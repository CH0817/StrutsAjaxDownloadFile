<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>JavaTest</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
    <script src="${pageContext.request.contextPath}/jquery/jquery-3.2.1.js"></script>
    <script src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
    <script>

        /**
         *檔案下載
         */
        function fileDownload() {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '${pageContext.request.contextPath}/fileDownload', true);
            xhr.responseType = 'blob';
            // onload: A JavaScript function object that gets invoked when the operation is successfully completed.
            xhr.onload = function (e) {
                if (this.status === 200) {
                    var blob = this.response;
                    var fileName = getFileName(xhr.getResponseHeader('Content-Disposition'));
                    saveFile(blob, decodeURIComponent(fileName));
                }
            };
            // onloadstart: A JavaScript function object that gets invoked exactly once when the operation begins.
            xhr.onloadstart = function () {
                $.messager.progress();
            };
            // onloadend: A JavaScript function object that gets invoked when the operation is completed for any reason; it will always follow a an abort, error, or load event.
            xhr.onloadend = function () {
                $.messager.progress('close');
            };
            xhr.send(getParamFormData());
        }

        /**
         * 將Object轉換成FormData
         */
        function getParamFormData() {
            var parmas = {
                id: 1,
                name: 'Rex',
                age: 34
            };
            var formData = new FormData();
            for (var key in parmas) {
                formData.append(key, parmas[key]);
            }
            return formData;
        }

        /**
         * 保存檔案
         * @param blob
         * @param fileName
         */
        function saveFile(blob, fileName) {
            if (navigator.appVersion.toString().indexOf('.NET') > 0) {
                //IE
                window.navigator.msSaveBlob(blob, fileName);
            } else {
                //other
                var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                a.href = url;
                a.download = fileName;
                a.click();
                window.URL.revokeObjectURL(url);
            }
        }

        /**
         * 取得檔案名稱
         * @param disposition
         * @returns {string}
         */
        function getFileName(disposition) {
            var fileName = '';
            if (disposition && disposition.indexOf('attachment') !== -1) {
                var fileNameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
                var matches = fileNameRegex.exec(disposition);
                if (matches !== null && matches[1]) {
                    fileName = matches[1].replace(/['"]/g, '');
                }
            }
            return fileName;
        }
    </script>
</head>
<body>
<a href="javascript:void(0);" onclick="fileDownload()">檔案下載</a><br>
</body>
</html>
