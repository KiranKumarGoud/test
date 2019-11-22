Vue.component("draw-structure-search", {
  template: `<div>
<table>
		<tr>
			<td><strong>Display options</strong></td>
			<td><input type="checkbox" id="chbx-carbonVis" /><label
				for="chbx-carbonVis">Carbon labels</label></td>
			<td><input type="checkbox" id="chbx-map" checked="checked" /><label
				for="chbx-map">Atom maps visible</label></td>
			<td><input type="checkbox" id="chbx-coloring" checked="checked" /><label
				for="chbx-coloring">CPK coloring</label></td>
		</tr>
	</table>
	<table width="70%" border="1" bordercolor="#febb80" class="sample"
		cellspacing="0" cellpadding="0">
		<tr>
			<td>

				<iframe id="sketch"
				
					src="<../../marvinjs/editorws.html" style="overflow: hidden; min-width: 750px; min-height: 350px; border: 1px solid darkgray;">
					</script>
				</iframe></td>
		</tr>
		<tr>
			<td align="center"><input type="submit"
				value="       submit      " id="btn-molExport"  /><input type="hidden" id="molsource" name="molsource" value=""/></td>
		</tr>

	</table>
<div class="molecule-div">
		<select id="list-inputformat">
			<option value="smiles">SMILES</option>
		</select>
	</div>
</div>`,
  mounted() {
    var marvinSketcherInstance;

    $(document).ready(function handleDocumentReady (e) {
      var p = MarvinJSUtil.getEditor("#sketch");
      console.log("pppppppp  "+p);
      p.then(function (sketcherInstance) {
        marvinSketcherInstance = sketcherInstance;
        initControl();
      }, function (error) {
        alert("Cannot retrieve sketcher instance from iframe:"+error);
      });
    });

    function initControl () {

      // get mol button
      $("#btn-molExport").on("click", function (e) {
        marvinSketcherInstance.exportStructure("smiles").then(function(source) {
          console.log("source  "+source);

          $("#molsource").text(source);
        }, function(error) {
          alert("Molecule export failed:"+error);
        });
      });
    }

  },

  data() {
    return {};
  },
  methods: {
    clickSubmit(){
      document.getElementById("btn-molExport").click()
    }
  }

});
