/**
 * Created by Eddie Jeon on 2017-02-27.
 */
function sendFile(file,editor) {
    var data = new FormData();
    data = new FormData();
    data.append("uploadFile", file);
    $.ajax ({
        data: data,
        type: "POST",
        url: "/cmm/fms/summerUploader.do",// this file uploads the picture and
        // returns a chain containing the path
        cache: false,
        dataType:'json',
        contentType: false,
        processData: false,
        success: function(result) {
           $(editor).summernote('editor.insertImage',"/cmm/fms/getImage.do?atchFileId="+result.atchFileId+"&fileSn="+result.fileSn);
        },
        error: function(data) {
            alert(data);
        }
    });

}

function sendPopupFile(file,editor) {
	var data = new FormData();
	data = new FormData();
	data.append("uploadFile", file);
	$.ajax ({
		data: data,
		type: "POST",
		url: "/cmm/fms/popupUploader.do",// this file uploads the picture and
		// returns a chain containing the path
		cache: false,
		dataType:'json',
		contentType: false,
		processData: false,
		success: function(result) {
			$(editor).summernote('editor.insertImage',"/cmm/fms/getImage.do?atchFileId="+result.atchFileId+"&fileSn="+result.fileSn);
		},
		error: function(data) {
			alert(data);
		}
	});
	
}

