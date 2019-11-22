<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta th:name="${_csrf.parameterName}" th:content="${_csrf.token}"/>
    <title>Create an account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <meta http-equiv="X-Frame-Options" content="sameorigin">

</head>
<body>
  <div class="container">
      Your Session is: ${usersession}

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <%--<form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>--%>

        <form id="logoutForm" action="${contextPath}/appLogout" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="currentSession" value="${usersession}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </c:if>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>


<script>
    /*jQuery.ajax({
        type: "GET",
        url: "${contextPath}/security/target/contains/${usersession}/123",
        dataType: "json",
        contentType: "application/json",
        success: function(msg){
            console.log(msg);
        }
    });
    var getstdnameInput =
        [
            {
                "synonyms": "12355",
                "targetId": 9461,
                "label": "12355",
                "value": "12355",
                "operator": "|",
                "stdnameId": 3167
            },
            {
                "synonyms": "12325",
                "targetId": 63593,
                "label": "12325",
                "value": "12325",
                "operator": "|",
                "stdnameId": 850
            },
            {
                "synonyms": "12326",
                "targetId": 63596,
                "label": "12326",
                "value": "12326",
                "operator": "|",
                "stdnameId": 853
            }
        ];

    jQuery.ajax({
        type: "POST",
        url: "${contextPath}/security/target/getstdname/${usersession}",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(getstdnameInput),
        success: function(msg){
            console.log(msg);
        }
    });

    jQuery.ajax({
        type: "GET",
        url: "${contextPath}/security/target/${usersession}",
        dataType: "json",
        contentType: "application/json",
        success: function(msg){
            // console.log(msg);
        }
    });

    jQuery.ajax({
        type: "GET",
        url: "${contextPath}/security/target/bysynonyms/${usersession}/12334",
        dataType: "json",
        contentType: "application/json",
        success: function(msg){
            console.log(msg);
        }
    });

    jQuery.ajax({
        type: "GET",
        url: "${contextPath}/security/target/contains/${usersession}/123",
        dataType: "json",
        contentType: "application/json",
        success: function(msg){
            console.log(msg);
        }
    });*/


    /*var countInput = [{"sourceOption":"Target","simpleSearch":{"option":"Target","targetSynonymsDTOList":[{"synonyms":"TESTIS SECRETORY SPERM-BINDING PROTEIN LI 233M","targetId":81278,"label":"TESTIS SECRETORY SPERM-BINDING PROTEIN LI 233M","value":"TESTIS SECRETORY SPERM-BINDING PROTEIN LI 233M","operator":"|","stdnameId":13710},{"synonyms":"TESTICULAR SECRETORY PROTEIN LI 54","targetId":78648,"label":"TESTICULAR SECRETORY PROTEIN LI 54","value":"TESTICULAR SECRETORY PROTEIN LI 54","operator":"|","stdnameId":13760}]},"advancedSearch":null,"operator":""}];*/
    // var countInput = [{"sourceOption":"Target","simpleSearch":{"option":"Target","targetSynonymsDTOList":[{"synonyms":"12346","targetId":2997,"label":"12346","value":"12346","operator":"|","stdnameId":887}]},"advancedSearch":null,"operator":"OR"},{"sourceOption":"Assay","simpleSearch":{"option":"Assay","ontoAssayTypeDTOList":[{"assaytypeOntId":14,"assayType":"B","parentOntId":4,"enddataPointLeaf":true,"ontLevel":3,"displyOrder":14,"assayTypeDesc":"Binding","label":"Binding","value":"Binding","operator":"|"}]},"advancedSearch":null,"operator":""}];
    //var countInput = [{"sourceOption":"Target","simpleSearch":null,"advancedSearch":{"option":"common","targetProteinMasterList":[{"targetId":79594,"source":"HUMAN","stdNameId":13705,"standardName":"TESTIS SPECIFIC SERINE KINASE 4","commonName":"TESTIS-SPECIFIC SERINE/THREONINE-PROTEIN KINASE 4","locusId":283629,"multipleLoci":null,"officialName":"TSSK4","uniprotId":"Q6SA08","sciSourceName":"HOMO SAPIENS","pdbId":null,"flag":"NOV17","flagNew":null,"label":"TESTIS-SPECIFIC SERINE/THREONINE-PROTEIN KINASE 4","value":"TESTIS-SPECIFIC SERINE/THREONINE-PROTEIN KINASE 4","operator":"|"},{"targetId":13301,"source":null,"stdNameId":4274,"standardName":"TESTOSTERONE-ESTRADIOL BINDING GLOBULIN","commonName":"TESTOSTERONE-ESTRADIOL BINDING GLOBULIN","locusId":null,"multipleLoci":null,"officialName":null,"uniprotId":null,"sciSourceName":null,"pdbId":null,"flag":null,"flagNew":null,"label":"TESTOSTERONE-ESTRADIOL BINDING GLOBULIN","value":"TESTOSTERONE-ESTRADIOL BINDING GLOBULIN","operator":"|"},{"targetId":13300,"source":"HUMAN","stdNameId":4274,"standardName":"TESTOSTERONE-ESTRADIOL BINDING GLOBULIN","commonName":"TESTOSTERONE-ESTRADIOL BINDING GLOBULIN","locusId":null,"multipleLoci":null,"officialName":null,"uniprotId":null,"sciSourceName":"HOMO SAPIENS","pdbId":null,"flag":null,"flagNew":null,"label":"TESTOSTERONE-ESTRADIOL BINDING GLOBULIN","value":"TESTOSTERONE-ESTRADIOL BINDING GLOBULIN","operator":"|"},{"targetId":79972,"source":null,"stdNameId":13705,"standardName":"TESTIS SPECIFIC SERINE KINASE 4","commonName":"TESTIS-SPECIFIC SERINE/THREONINE-PROTEIN KINASE 4","locusId":null,"multipleLoci":null,"officialName":null,"uniprotId":null,"sciSourceName":null,"pdbId":null,"flag":"JAN18","flagNew":null,"label":"TESTIS-SPECIFIC SERINE/THREONINE-PROTEIN KINASE 4","value":"TESTIS-SPECIFIC SERINE/THREONINE-PROTEIN KINASE 4","operator":"|"},{"targetId":74777,"source":"HUMAN","stdNameId":12535,"standardName":"TESTIS-SPECIFIC SERINE KINASE 6","commonName":"TESTIS-SPECIFIC SERINE KINASE 6","locusId":83983,"multipleLoci":null,"officialName":"TSSK6","uniprotId":"Q9BXA6","sciSourceName":"HOMO SAPIENS","pdbId":null,"flag":"AUG14","flagNew":null,"label":"TESTIS-SPECIFIC SERINE KINASE 6","value":"TESTIS-SPECIFIC SERINE KINASE 6","operator":"|"},{"targetId":70440,"source":null,"stdNameId":12535,"standardName":"TESTIS-SPECIFIC SERINE KINASE 6","commonName":"TESTIS-SPECIFIC SERINE KINASE 6","locusId":null,"multipleLoci":null,"officialName":"TSSK6","uniprotId":null,"sciSourceName":null,"pdbId":null,"flag":"JUL13","flagNew":null,"label":"TESTIS-SPECIFIC SERINE KINASE 6","value":"TESTIS-SPECIFIC SERINE KINASE 6","operator":"|"},{"targetId":40511,"source":null,"stdNameId":4797,"standardName":"TESTIS-SPECIFIC SERINE KINASE 3","commonName":"TESTIS-SPECIFIC SERINE KINASE 3","locusId":null,"multipleLoci":null,"officialName":"TSSK3","uniprotId":null,"sciSourceName":null,"pdbId":null,"flag":null,"flagNew":null,"label":"TESTIS-SPECIFIC SERINE KINASE 3","value":"TESTIS-SPECIFIC SERINE KINASE 3","operator":"|"},{"targetId":74807,"source":"HUMAN","stdNameId":4797,"standardName":"TESTIS-SPECIFIC SERINE KINASE 3","commonName":"TESTIS-SPECIFIC SERINE KINASE 3","locusId":81629,"multipleLoci":null,"officialName":"TSSK3","uniprotId":"Q96PN8","sciSourceName":"HOMO SAPIENS","pdbId":null,"flag":"AUG14","flagNew":null,"label":"TESTIS-SPECIFIC SERINE KINASE 3","value":"TESTIS-SPECIFIC SERINE KINASE 3","operator":"|"}],"fileData":[],"targetCategory":["Primary","Secondary","Profile"]},"operator":""}];

   // var countInput = [{"sourceOption":"Target","simpleSearch":{"option":"Target","targetSynonymsDTOList":[{"synonyms":"TESTICULAR TISSUE PROTEIN LI 86","targetId":79677,"label":"TESTICULAR TISSUE PROTEIN LI 86","value":"TESTICULAR TISSUE PROTEIN LI 86","operator":"&","stdnameId":13879},{"synonyms":"TESTIS FATTY ACID BINDING PROTEIN","targetId":77969,"label":"TESTIS FATTY ACID BINDING PROTEIN","value":"TESTIS FATTY ACID BINDING PROTEIN","operator":"!","stdnameId":13660},{"synonyms":"TESTILIN","targetId":80721,"label":"TESTILIN","value":"TESTILIN","operator":"|","stdnameId":14045},{"synonyms":"TESTICULAR SECRETORY PROTEIN LI 54","targetId":78648,"label":"TESTICULAR SECRETORY PROTEIN LI 54","value":"TESTICULAR SECRETORY PROTEIN LI 54","operator":"|","stdnameId":13760},{"synonyms":"TESTIS-SPECIFIC PROTEIN KINASE 2","targetId":40509,"label":"TESTIS-SPECIFIC PROTEIN KINASE 2","value":"TESTIS-SPECIFIC PROTEIN KINASE 2","operator":"|","stdnameId":4795},{"synonyms":"TESTIS SECRETORY SPERM-BINDING PROTEIN LI 233M","targetId":81278,"label":"TESTIS SECRETORY SPERM-BINDING PROTEIN LI 233M","value":"TESTIS SECRETORY SPERM-BINDING PROTEIN LI 233M","operator":"|","stdnameId":13710},{"synonyms":"TESTICULAR TISSUE PROTEIN LI 118","targetId":81657,"label":"TESTICULAR TISSUE PROTEIN LI 118","value":"TESTICULAR TISSUE PROTEIN LI 118","operator":"|","stdnameId":14212},{"synonyms":"TESTIS TISSUE SPERM-BINDING PROTEIN LI 38A","targetId":81507,"label":"TESTIS TISSUE SPERM-BINDING PROTEIN LI 38A","value":"TESTIS TISSUE SPERM-BINDING PROTEIN LI 38A","operator":"|","stdnameId":14200}]},"advancedSearch":null,"operator":"OR"},{"sourceOption":"Assay","simpleSearch":{"option":"Assay","ontoAssayTypeDTOList":[{"assaytypeOntId":30,"assayType":"ELP","parentOntId":13,"enddataPointLeaf":true,"ontLevel":4,"displyOrder":30,"assayTypeDesc":"Experimental Log P","label":"Experimental Log P","value":"Experimental Log P","operator":"!"},{"assaytypeOntId":14,"assayType":"B","parentOntId":4,"enddataPointLeaf":true,"ontLevel":3,"displyOrder":14,"assayTypeDesc":"Binding","label":"Binding","value":"Binding","operator":"|"},{"assaytypeOntId":15,"assayType":"B/M","parentOntId":4,"enddataPointLeaf":true,"ontLevel":3,"displyOrder":15,"assayTypeDesc":"Binding (with mutant)","label":"Binding (with mutant)","value":"Binding (with mutant)","operator":"&"}]},"advancedSearch":null,"operator":""}];
   // var countInput = [{"sourceOption":"Target","simpleSearch":{"option":"Target","targetSynonymsDTOList":[{"synonyms":"12374","targetId":79554,"label":"12374","value":"12374","operator":"|","stdnameId":843},{"synonyms":"12322","targetId":63591,"label":"12322","value":"12322","operator":"|","stdnameId":847},{"synonyms":"12346","targetId":2997,"label":"12346","value":"12346","operator":"|","stdnameId":887},{"synonyms":"1235","targetId":40333,"label":"1235","value":"1235","operator":"|","stdnameId":1058},{"synonyms":"1234","targetId":3506,"label":"1234","value":"1234","operator":"|","stdnameId":1057},{"synonyms":"12387","targetId":3245,"label":"12387","value":"12387","operator":"|","stdnameId":959},{"synonyms":"12366","targetId":3169,"label":"12366","value":"12366","operator":"|","stdnameId":947}]},"advancedSearch":null,"operator":""}];
   // var countInput = [{"sourceOption":"Target","simpleSearch":{"option":"Target","targetSynonymsDTOList":[{"synonyms":"12374","targetId":79554,"label":"12374","value":"12374","operator":"|","stdnameId":843},{"synonyms":"12322","targetId":63591,"label":"12322","value":"12322","operator":"|","stdnameId":847},{"synonyms":"12346","targetId":2997,"label":"12346","value":"12346","operator":"|","stdnameId":887},{"synonyms":"1235","targetId":40333,"label":"1235","value":"1235","operator":"|","stdnameId":1058},{"synonyms":"1234","targetId":3506,"label":"1234","value":"1234","operator":"|","stdnameId":1057},{"synonyms":"12387","targetId":3245,"label":"12387","value":"12387","operator":"|","stdnameId":959},{"synonyms":"12366","targetId":3169,"label":"12366","value":"12366","operator":"|","stdnameId":947}]},"advancedSearch":null,"operator":"AND"},{"sourceOption":"Assay","simpleSearch":{"option":"Assay","ontoAssayTypeDTOList":[{"assaytypeOntId":25,"assayType":"C","parentOntId":12,"enddataPointLeaf":true,"ontLevel":4,"displyOrder":25,"assayTypeDesc":"Calculated","label":"Calculated","value":"Calculated","operator":"|"}]},"advancedSearch":null,"operator":""}];
   //var countInput = [{"sourceOption":"Assay","simpleSearch":{"option":"Assay","ontoAssayTypeDTOList":[{"assaytypeOntId":14,"assayType":"B","parentOntId":4,"enddataPointLeaf":true,"ontLevel":3,"displyOrder":14,"assayTypeDesc":"Binding","label":"Binding","value":"Binding","operator":"|"}]},"advancedSearch":null,"operator":"AND"},{"sourceOption":"Target","simpleSearch":{"option":"Target","targetSynonymsDTOList":[{"synonyms":"12374","targetId":79554,"label":"12374","value":"12374","operator":"|","stdnameId":843},{"synonyms":"12322","targetId":63591,"label":"12322","value":"12322","operator":"|","stdnameId":847},{"synonyms":"12346","targetId":2997,"label":"12346","value":"12346","operator":"|","stdnameId":887},{"synonyms":"1235","targetId":40333,"label":"1235","value":"1235","operator":"|","stdnameId":1058},{"synonyms":"1234","targetId":3506,"label":"1234","value":"1234","operator":"|","stdnameId":1057},{"synonyms":"12387","targetId":3245,"label":"12387","value":"12387","operator":"|","stdnameId":959},{"synonyms":"12366","targetId":3169,"label":"12366","value":"12366","operator":"|","stdnameId":947}]},"advancedSearch":null,"operator":""}];
   /*var countInput = [{"sourceOption": "Assay","simpleSearch": {"option": "Assay","ontoAssayTypeDTOList": [{"assaytypeOntId": 14,"assayType": "B","parentOntId": 4,"enddataPointLeaf": true,"ontLevel": 3,"displyOrder": 14,"assayTypeDesc": "Binding","label": "Binding","value": "Binding","operator": "|"}]},"advancedSearch": null,"operator": "OR"},{"sourceOption": "Structure","simpleSearch": {"option": "Structure","compoundSynonymsDTOList": [{"strId": 2729079,"compoundSynonym": "AMI-121","cmpdType": null,"label": "AMI-121","value": "AMI-121","operator": "|"}, {"strId": 3278254,"compoundSynonym": "AMI-25","cmpdType": null,"label": "AMI-25","value": "AMI-25","operator": "|"}, {"strId": 2086718,"compoundSynonym": "AMI-ANELUN","cmpdType": null,"label": "AMI-ANELUN","value": "AMI-ANELUN","operator": "|"}, {"strId": 705960,"compoundSynonym": "AMIBEGRON","cmpdType": "D","label": "AMIBEGRON","value": "AMIBEGRON","operator": "|"}, {"strId": 3679705,"compoundSynonym": "AMICALIQ","cmpdType": "D","label": "AMICALIQ","value": "AMICALIQ","operator": "|"}]},"advancedSearch": null,"operator": "OR"}, {"sourceOption": "Target","simpleSearch": {"option": "Target","targetSynonymsDTOList": [{"synonyms": "12374","targetId": 79554,"label": "12374","value": "12374","operator": "|","stdnameId": 843}, {"synonyms": "12322","targetId": 63591,"label": "12322","value": "12322","operator": "|","stdnameId": 847}, {"synonyms": "12346","targetId": 2997,"label": "12346","value": "12346","operator": "|","stdnameId": 887}, {"synonyms": "1235","targetId": 40333,"label": "1235","value": "1235","operator": "|","stdnameId": 1058}, {"synonyms": "1234","targetId": 3506,"label": "1234","value": "1234","operator": "|","stdnameId": 1057}, {"synonyms": "12387","targetId": 3245,"label": "12387","value": "12387","operator": "|","stdnameId": 959}, {"synonyms": "12366","targetId": 3169,"label": "12366","value": "12366","operator": "|","stdnameId": 947}]},"advancedSearch": null,"operator": ""}];
    jQuery.ajax({
        type: "POST",
        url: "${contextPath}/security/allmapping/search/count/${usersession}",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(countInput),
        success: function(msg){
            console.log(msg);
        }
    });*/

    /*jQuery.ajax({
        type: "GET",
        url: "${contextPath}/security/targetprotein/byoption/office/tes/${usersession}",
        dataType: "json",
        contentType: "application/json",
        success: function(msg){
            console.log(msg);
        }
    });

    jQuery.ajax({
        type: "GET",
        url: "${contextPath}/security/targetprotein/byoption/common/tes/${usersession}",
        dataType: "json",
        contentType: "application/json",
        success: function(msg){
            console.log(msg);
        }
    });

    // upprot - p4
    // pdb - 1d0
    // enterez - 123
    // office - tes
    // common - tes
    jQuery.ajax({
        type: "GET",
        url: "${contextPath}/security/targetprotein/byoption/enterez/123/${usersession}",
        dataType: "json",
        contentType: "application/json",
        success: function(msg){
            console.log(msg);
        }
    });

    jQuery.ajax({
        type: "GET",
        url: "${contextPath}/security/targetprotein/byoption/upprot/p4/${usersession}",
        dataType: "json",
        contentType: "application/json",
        success: function(msg){
            console.log(msg);
        }
    });

    jQuery.ajax({
        type: "GET",
        url: "${contextPath}/security/targetprotein/byoption/pdb/1d0/${usersession}",
        dataType: "json",
        contentType: "application/json",
        success: function(msg){
            console.log(msg);
        }
    });*/



   /***
    * added the test for to check the issue of the advance search issue for count
    */
    var countInput = [{"sourceOption":"Target","simpleSearch":null,"advancedSearch":{"option":"common","targetProteinMasterList":[{"targetId":5490,"source":"RAT","stdNameId":1683,"standardName":"EPIDERMAL GROWTH FACTOR RECEPTOR (ERYTHROBLASTIC LEUKEMIA VIRAL (V-ERB-B) ONCOGENE HOMOLOG, AVIAN)","commonName":"EGFR","locusId":24329,"multipleLoci":null,"officialName":"EGFR","uniprotId":null,"sciSourceName":"RATTUS NORVEGICUS","pdbId":null,"flag":null,"flagNew":null,"label":"EGFR","value":"EGFR","operator":"|"}],"fileData":[],"targetCategory":["Primary","Secondary","Profile"]},"operator":""}];

    jQuery.ajax({
        type: "POST",
        url: "${contextPath}/security/allmapping/search/count/${usersession}",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(countInput),
        success: function(msg) {
            console.log(msg);
        }
    });

    /***
     * added the test for to check for file csv download
     */

    var csvFileData = {"activity":[1432504 ,1521035 ,2197243],"assay":[79438,85122,87651],"reference":[2099192],"structure":[377708]};

    jQuery.ajax({
        type: "POST",
        url: "${contextPath}/security/v1/tabularview/export/download/filename/${usersession}",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(csvFileData),
        success: function(msg) {
            console.log(msg);
        }
    });

    /*var csvVisualizationData ;

    jQuery.ajax({
        type: "POST",
        url: "${contextPath}/security/v1/tabularview/export/download/filename/fromtemptable/${usersession}",
        dataType: "json",
        contentType: "application/json",
        success: function(msg) {
            console.log(msg);
        }
    });*/

    // var searchInput = [{"sourceOption":"Target","simpleSearch":{"option":"Target","targetSynonymsDTOList":[{"synonyms":"12374","targetId":79554,"label":"12374","value":"12374","operator":"|","stdnameId":843},{"synonyms":"12322","targetId":63591,"label":"12322","value":"12322","operator":"|","stdnameId":847},{"synonyms":"1232","targetId":3490,"label":"1232","value":"1232","operator":"|","stdnameId":1055},{"synonyms":"1235","targetId":40333,"label":"1235","value":"1235","operator":"|","stdnameId":1058},{"synonyms":"1234","targetId":3506,"label":"1234","value":"1234","operator":"|","stdnameId":1057},{"synonyms":"12387","targetId":3245,"label":"12387","value":"12387","operator":"|","stdnameId":959},{"synonyms":"12367","targetId":3176,"label":"12367","value":"12367","operator":"|","stdnameId":948},{"synonyms":"12366","targetId":3169,"label":"12366","value":"12366","operator":"|","stdnameId":947}]},"advancedSearch":null,"operator":""}];
   // var searchInput = [{"sourceOption":"Target","simpleSearch":{"option":"Target","targetSynonymsDTOList":[{"synonyms":"TESTICULAR TISSUE PROTEIN LI 86","targetId":79677,"label":"TESTICULAR TISSUE PROTEIN LI 86","value":"TESTICULAR TISSUE PROTEIN LI 86","operator":"&","stdnameId":13879},{"synonyms":"TESTIS FATTY ACID BINDING PROTEIN","targetId":77969,"label":"TESTIS FATTY ACID BINDING PROTEIN","value":"TESTIS FATTY ACID BINDING PROTEIN","operator":"!","stdnameId":13660},{"synonyms":"TESTILIN","targetId":80721,"label":"TESTILIN","value":"TESTILIN","operator":"|","stdnameId":14045},{"synonyms":"TESTICULAR SECRETORY PROTEIN LI 54","targetId":78648,"label":"TESTICULAR SECRETORY PROTEIN LI 54","value":"TESTICULAR SECRETORY PROTEIN LI 54","operator":"|","stdnameId":13760},{"synonyms":"TESTIS-SPECIFIC PROTEIN KINASE 2","targetId":40509,"label":"TESTIS-SPECIFIC PROTEIN KINASE 2","value":"TESTIS-SPECIFIC PROTEIN KINASE 2","operator":"|","stdnameId":4795},{"synonyms":"TESTIS SECRETORY SPERM-BINDING PROTEIN LI 233M","targetId":81278,"label":"TESTIS SECRETORY SPERM-BINDING PROTEIN LI 233M","value":"TESTIS SECRETORY SPERM-BINDING PROTEIN LI 233M","operator":"|","stdnameId":13710},{"synonyms":"TESTICULAR TISSUE PROTEIN LI 118","targetId":81657,"label":"TESTICULAR TISSUE PROTEIN LI 118","value":"TESTICULAR TISSUE PROTEIN LI 118","operator":"|","stdnameId":14212},{"synonyms":"TESTIS TISSUE SPERM-BINDING PROTEIN LI 38A","targetId":81507,"label":"TESTIS TISSUE SPERM-BINDING PROTEIN LI 38A","value":"TESTIS TISSUE SPERM-BINDING PROTEIN LI 38A","operator":"|","stdnameId":14200}]},"advancedSearch":null,"operator":"OR"},{"sourceOption":"Assay","simpleSearch":{"option":"Assay","ontoAssayTypeDTOList":[{"assaytypeOntId":30,"assayType":"ELP","parentOntId":13,"enddataPointLeaf":true,"ontLevel":4,"displyOrder":30,"assayTypeDesc":"Experimental Log P","label":"Experimental Log P","value":"Experimental Log P","operator":"!"},{"assaytypeOntId":14,"assayType":"B","parentOntId":4,"enddataPointLeaf":true,"ontLevel":3,"displyOrder":14,"assayTypeDesc":"Binding","label":"Binding","value":"Binding","operator":"|"},{"assaytypeOntId":15,"assayType":"B/M","parentOntId":4,"enddataPointLeaf":true,"ontLevel":3,"displyOrder":15,"assayTypeDesc":"Binding (with mutant)","label":"Binding (with mutant)","value":"Binding (with mutant)","operator":"&"}]},"advancedSearch":null,"operator":""}];
   // var searchInput = [{"sourceOption":"Assay","simpleSearch":{"option":"Assay","ontoAssayTypeDTOList":[{"assaytypeOntId":14,"assayType":"B","parentOntId":4,"enddataPointLeaf":true,"ontLevel":3,"displyOrder":14,"assayTypeDesc":"Binding","label":"Binding","value":"Binding","operator":"|"}]},"advancedSearch":null,"operator":"AND"},{"sourceOption":"Target","simpleSearch":{"option":"Target","targetSynonymsDTOList":[{"synonyms":"12374","targetId":79554,"label":"12374","value":"12374","operator":"|","stdnameId":843},{"synonyms":"12322","targetId":63591,"label":"12322","value":"12322","operator":"|","stdnameId":847},{"synonyms":"12346","targetId":2997,"label":"12346","value":"12346","operator":"|","stdnameId":887},{"synonyms":"1235","targetId":40333,"label":"1235","value":"1235","operator":"|","stdnameId":1058},{"synonyms":"1234","targetId":3506,"label":"1234","value":"1234","operator":"|","stdnameId":1057},{"synonyms":"12387","targetId":3245,"label":"12387","value":"12387","operator":"|","stdnameId":959},{"synonyms":"12366","targetId":3169,"label":"12366","value":"12366","operator":"|","stdnameId":947}]},"advancedSearch":null,"operator":""}];
    // var searchInput = [{"sourceOption": "Assay","simpleSearch": {"option": "Assay","ontoAssayTypeDTOList": [{"assaytypeOntId": 14,"assayType": "B","parentOntId": 4,"enddataPointLeaf": true,"ontLevel": 3,"displyOrder": 14,"assayTypeDesc": "Binding","label": "Binding","value": "Binding","operator": "|"}]},"advancedSearch": null,"operator": "OR"},{"sourceOption": "Structure","simpleSearch": {"option": "Structure","compoundSynonymsDTOList": [{"strId": 2729079,"compoundSynonym": "AMI-121","cmpdType": null,"label": "AMI-121","value": "AMI-121","operator": "|"}, {"strId": 3278254,"compoundSynonym": "AMI-25","cmpdType": null,"label": "AMI-25","value": "AMI-25","operator": "|"}, {"strId": 2086718,"compoundSynonym": "AMI-ANELUN","cmpdType": null,"label": "AMI-ANELUN","value": "AMI-ANELUN","operator": "|"}, {"strId": 705960,"compoundSynonym": "AMIBEGRON","cmpdType": "D","label": "AMIBEGRON","value": "AMIBEGRON","operator": "|"}, {"strId": 3679705,"compoundSynonym": "AMICALIQ","cmpdType": "D","label": "AMICALIQ","value": "AMICALIQ","operator": "|"}]},"advancedSearch": null,"operator": "OR"}, {"sourceOption": "Target","simpleSearch": {"option": "Target","targetSynonymsDTOList": [{"synonyms": "12374","targetId": 79554,"label": "12374","value": "12374","operator": "|","stdnameId": 843}, {"synonyms": "12322","targetId": 63591,"label": "12322","value": "12322","operator": "|","stdnameId": 847}, {"synonyms": "12346","targetId": 2997,"label": "12346","value": "12346","operator": "|","stdnameId": 887}, {"synonyms": "1235","targetId": 40333,"label": "1235","value": "1235","operator": "|","stdnameId": 1058}, {"synonyms": "1234","targetId": 3506,"label": "1234","value": "1234","operator": "|","stdnameId": 1057}, {"synonyms": "12387","targetId": 3245,"label": "12387","value": "12387","operator": "|","stdnameId": 959}, {"synonyms": "12366","targetId": 3169,"label": "12366","value": "12366","operator": "|","stdnameId": 947}]},"advancedSearch": null,"operator": ""}];
    // var searchInput = [{"sourceOption":"Target","simpleSearch":{"option":"Target","targetSynonymsDTOList":[{"synonyms":"12374","targetId":79554,"label":"12374","value":"12374","operator":"|","stdnameId":843},{"synonyms":"12322","targetId":63591,"label":"12322","value":"12322","operator":"|","stdnameId":847},{"synonyms":"12387","targetId":3245,"label":"12387","value":"12387","operator":"|","stdnameId":959},{"synonyms":"1237","targetId":3517,"label":"1237","value":"1237","operator":"|","stdnameId":1060},{"synonyms":"12367","targetId":3176,"label":"12367","value":"12367","operator":"|","stdnameId":948},{"synonyms":"12346","targetId":2997,"label":"12346","value":"12346","operator":"|","stdnameId":887},{"synonyms":"1234","targetId":3506,"label":"1234","value":"1234","operator":"|","stdnameId":1057},{"synonyms":"1232","targetId":3490,"label":"1232","value":"1232","operator":"|","stdnameId":1055},{"synonyms":"12366","targetId":3169,"label":"12366","value":"12366","operator":"|","stdnameId":947},{"synonyms":"1235","targetId":40333,"label":"1235","value":"1235","operator":"|","stdnameId":1058}]},"advancedSearch":null,"operator":"AND"},{"sourceOption":"Assay","simpleSearch":{"option":"Assay","ontoAssayTypeDTOList":[{"assaytypeOntId":25,"assayType":"C","parentOntId":12,"enddataPointLeaf":true,"ontLevel":4,"displyOrder":25,"assayTypeDesc":"Calculated","label":"Calculated","value":"Calculated","operator":"|"}]},"advancedSearch":null,"operator":""}];
    var searchInput = [{"sourceOption":"Assay","simpleSearch":{"option":"Assay","ontoAssayTypeDTOList":[{"assaytypeOntId":14,"assayType":"B","parentOntId":4,"enddataPointLeaf":true,"ontLevel":3,"displyOrder":14,"assayTypeDesc":"Binding","label":"Binding","value":"Binding","operator":"|"}]},"advancedSearch":null,"operator":""}];
    jQuery.ajax({
        type: "POST",
        url: "${contextPath}/security/allmapping/search/visualization/${usersession}",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(searchInput),
        success: function(msg){
            console.log(msg);

            /*jQuery.ajax({
                type: "GET",
                url: "${contextPath}/security/allmapping/search/visualization/classification/${usersession}",
                dataType: "json",
                contentType: "application/json",
                success: function(msg){
                    console.log(msg);

                    jQuery.ajax({
                        type: "GET",
                        url: "${contextPath}/security/allmapping/search/visualization/yearwise/${usersession}",
                        dataType: "json",
                        contentType: "application/json",
                        success: function(msg){
                            console.log(msg);

                            jQuery.ajax({
                                type: "GET",
                                url: "${contextPath}/security/allmapping/search/visualization/bibliography/${usersession}",
                                dataType: "json",
                                contentType: "application/json",
                                success: function(msg){
                                    console.log(msg);
                                    jQuery.ajax({
                                        type: "GET",
                                        url: "${contextPath}/security/allmapping/search/visualization/indication/${usersession}",
                                        dataType: "json",
                                        contentType: "application/json",
                                        success: function(msg){
                                            console.log(msg);
                                        }
                                    });

                                }
                            });
                        }
                    });
                }
            });*/


            /*jQuery.ajax({
                type: "GET",
                url: "${contextPath}/security/allmapping/search/visualization/classification/${usersession}",
                dataType: "json",
                async : true,
                contentType: "application/json",
                success: function(msg){
                    console.log(msg);
                }
            });

            jQuery.ajax({
                type: "GET",
                url: "${contextPath}/security/allmapping/search/visualization/yearwise/${usersession}",
                dataType: "json",
                async : true,
                contentType: "application/json",
                success: function(msg){
                    console.log(msg);
                }
            });

            jQuery.ajax({
                type: "GET",
                url: "${contextPath}/security/allmapping/search/visualization/bibliography/${usersession}",
                dataType: "json",
                async : true,
                contentType: "application/json",
                success: function(msg){
                    console.log(msg);
                }
            });

            jQuery.ajax({
                type: "GET",
                url: "${contextPath}/security/allmapping/search/visualization/indication/${usersession}",
                dataType: "json",
                async : true,
                contentType: "application/json",
                success: function(msg){
                    console.log(msg);
                }
            });*/


            var one = jQuery.ajax({
                type: "GET",
                url: "${contextPath}/security/allmapping/search/visualization/classification/${usersession}",
                dataType: "json",
                contentType: "application/json",
                success: callback
            });

            var two = jQuery.ajax({
                type: "GET",
                url: "${contextPath}/security/allmapping/search/visualization/yearwise/${usersession}",
                dataType: "json",
                contentType: "application/json",
                success: callback
            });

            var three = jQuery.ajax({
                type: "GET",
                url: "${contextPath}/security/allmapping/search/visualization/bibliography/${usersession}",
                dataType: "json",
                contentType: "application/json",
                success: callback
            });

            var four = jQuery.ajax({
                type: "GET",
                url: "${contextPath}/security/allmapping/search/visualization/indication/${usersession}",
                dataType: "json",
                contentType: "application/json",
                success: callback
            });

            jQuery.when(one, two, three, four).then(function(){
                $('.result').text('Overall process used '+(new Date().getTime() - start) + 'ms.');
            });


       }
    });

    function callback(response) {
        // $('.progress').append('it took ' + (new Date().getTime() - start) + 'ms to fetch:').append(response);
        console.log(response);
    }
</script>
</body>
</html>
