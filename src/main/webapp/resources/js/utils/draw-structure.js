

var marvinController,
    inputController;

$(document).ready(function handleDocumentReady (e) {
    MarvinJSUtil.getEditor("#sketch").then(function (sketcherInstance) {

        marvinController = new MarvinControllerClass(
            sketcherInstance,
            $("#chbx-coloring"),
            $("#chbx-map"),
            $("#chbx-carbonVis")
        );

        inputController = new InputControllerClass(
            $("#btn-setMdlmolfile"),
            $("#btn-paste"),
            $("#list-inputformat"),
            $("#btn-clearTxt"),
            $("#btn-molExport"),
            $("#molsource-box")
        );

    }, function () {
        alert("Cannot retrieve sketcher instance from iframe");
    });

    //$("#molsource-box").val(caffeineSource);
});

var InputControllerClass = (function () {

    var delay = 5;

    function InputControllerClass ( importButton,
                                    pasteButton,
                                    formatDropdown,
                                    resetButton,
                                    molExportButton,
                                    srcTextBox ) {

        this.importButton		= importButton;
        this.pasteButton		= pasteButton;
        this.formatDropdown		= formatDropdown;
        this.resetButton		= resetButton;
        this.molExportButton	= molExportButton;
        this.srcTextBox 		= srcTextBox;
        this.init();
    }

    InputControllerClass.prototype.init = function init () {
        this.importButton.on("click", $.proxy(this.handleImportBtnClick, this));
        this.pasteButton.on("click", $.proxy(this.handlePasteBtnClick, this));
        this.resetButton.on("click", $.proxy(this.clearTxt, this));
        this.molExportButton.on("click", $.proxy(this.handleMolExportBtnClick, this));
    };

    InputControllerClass.prototype.handleImportBtnClick = function handleImportBtnClick (e) {
        this.delayCall(importMolAction, [this.getTxt(), this.getFormat()]);
    };

    InputControllerClass.prototype.handlePasteBtnClick = function handleImportBtnClick (e) {
        this.delayCall(pasteMolAction, [this.getTxt(), this.getFormat()]);
    };

    InputControllerClass.prototype.handleMolExportBtnClick = function handleMolExportBtnClick (e) {
        this.delayCall(exportMolAction, [this.getFormat()]);
    };



    InputControllerClass.prototype.delayCall = function delayCall (method, args) {
        setTimeout(function () {
            method.apply(null, args);
        }, delay);
    };

    InputControllerClass.prototype.getTxt = function getTxt () {
        return this.srcTextBox.val();
    };

    InputControllerClass.prototype.setTxt = function setTxt (txt) {
        this.srcTextBox.val(txt);
    };

    InputControllerClass.prototype.clearTxt = function clearTxt () {
        this.setTxt("");
    };

    InputControllerClass.prototype.getFormat = function getFormat (e) {
        return this.formatDropdown.val();
    };

    return InputControllerClass;

}());

var MarvinControllerClass = (function () {

    function MarvinControllerClass ( sketcherInstance, cpkCheckbox, mapCheckbox, carbonCheckbox) {
        this.sketcherInstance 	= sketcherInstance;
        this.cpkCheckbox 		= cpkCheckbox;
        this.mapCheckbox 		= mapCheckbox;
        this.carbonCheckbox 	= carbonCheckbox;
        this.init();
    }

    MarvinControllerClass.prototype.init = function init () {
        this.carbonCheckbox.on("change", $.proxy(this.handleCarbonCheckBoxChange, this));
        this.mapCheckbox.on("change", $.proxy(this.handleMapCheckBoxChange, this));
        this.cpkCheckbox.on("change", $.proxy(this.handleCpkCheckBoxChange, this));
    };

    MarvinControllerClass.prototype.handleCarbonCheckBoxChange = function handleCarbonCheckBoxChange (e) {
        this.sketcherInstance.setDisplaySettings({
            "carbonLabelVisible" : this.carbonCheckbox.is(':checked')
        });
    };

    MarvinControllerClass.prototype.handleMapCheckBoxChange = function handleMapCheckBoxChange (e) {
        this.sketcherInstance.setDisplaySettings({
            "atomMapsVisible" : this.mapCheckbox.is(':checked')
        });
    };

    MarvinControllerClass.prototype.handleCpkCheckBoxChange = function handleCpkCheckBoxChange (e) {
        this.sketcherInstance.setDisplaySettings({
            "cpkColoring" : this.cpkCheckbox.is(':checked')
        });
    };

    return MarvinControllerClass;

}());



function pasteMolAction (txt, format) {
    var pastePromise = marvinController.sketcherInstance.pasteStructure(format, txt);
    pastePromise.then(function() {}, function(error) {
        alert(error);
    });
}

function importMolAction (txt, format) {
    var importPromise = marvinController.sketcherInstance.importStructure(format, txt);
    importPromise.then(function() {}, function(error) {
        alert(error);
    });
}

function exportMolAction (format) {
    alert("source   ");
    var ic = inputController;

    var exportPromise = marvinController.sketcherInstance.exportStructure(format, null);
    exportPromise.then(function(source) {
        alert("source123   "+source);
        ic.setTxt(source);
        alert("source   "+source);
        //opener.document.basicSearchForm.txtsmile.value = source;
        if(source.length > 0){
            $("#smile").val(source);
            $("#gvkId").prop("disabled", true);
            $("#structureId").prop("disabled", true);
            $("#compondNameId").prop("disabled", true);
            $("#casNoId").prop("disabled", true);
            //loadphasedetails("smile");
            var formData = $("#structure_form").serialize()+"&field_level=smile";
            jQuery.ajax(
                {

                    type: 'POST',
                    url:  app_url + "/loadDatForPhaseDetails",
                    data:formData,
                    success:function(result){
                        $("#phasedetails").find('option').remove();
                        jQuery('<option>').val("").text("-Select-").appendTo('[id^="phasedetails"]');

                        if(jQuery.isEmptyObject(result) ==false){
                            jQuery.each(result, function(index, value) {
                                jQuery('<option>').val(value).text(value).appendTo('[id^="phasedetails"]');

                            });
                        }
                    }
                });

            //self.close();
            $("#smileModal").modal('hide');


        }else{
            alert(" Smile Structure is Empty");
            $("#gvkId").prop("disabled", false);
            $("#structureId").prop("disabled", false);
            $("#compondNameId").prop("disabled", false);
            $("#casNoId").prop("disabled", false);

            return false;
        }


    }, function(error) {
        ic.setTxt(error);
    });
}

