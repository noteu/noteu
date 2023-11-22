$(document).ready(function() {
    var selectedFiles = []; // 선택한 파일 목록을 저장할 배열

    var fileArr = [];
    var fileIds = $(".fileId");

    fileIds.each(function (){
       fileArr.push($(this).val());
    });

    console.log(fileArr);

    // 파일 선택 시 파일 미리보기 추가
    $('#files').change(function() {
        var files = $(this).prop("files");

        // 새로운 파일 미리보기 추가
        for (var i = 0; i < files.length; i++) {
            $.addFilePreview(files[i]);
            selectedFiles.push(files[i]); // 선택한 파일을 selectedFiles 배열에 추가
        }
    });

    // 수정 클릭 시
    $("#submitBtn").click(function() {
        var subjectId = $("#subjectId").val();
        var referencId = $("#referenceId").val();

        // formData 생성
        var formData = new FormData();
        // 제목과 내용 입력값 가져오기
        var title = $("#title").val();
        var content = $("#content").val();
        formData.append("referenceRoomTitle", title);
        formData.append("referenceRoomContent", content);

        if (selectedFiles.length === 0 && fileArr.length === 0) {
            alert("파일을 추가해 주세요.");
            return;
        }

        for(var j = 0; j < fileArr.length; j++) {
            formData.append("referenceId", fileArr[j]);
        }

        for (var i = 0; i < selectedFiles.length; i++) {
            formData.append("referenceFile", selectedFiles[i]);
        }

        // FormData의 key 확인
        for (let key of formData.keys()) {
            console.log(key);
        }

        // FormData의 value 확인
        for (let value of formData.values()) {
            console.log(value);
        }

        // AJAX 요청 생성
        $.ajax({
            url: "/subjects/" + subjectId + "/references/edit/" + referencId,
            type: "POST",
            data: formData,
            enctype: "multipart/form-data",
            processData: false,
            contentType: false,
            success: function(response) {
                console.log("데이터 전송 성공");
                location.href = "/subjects/" + subjectId + "/references/" + referencId;
            },
            error: function() {
                console.error("데이터 전송 실패");
            }
        });
    });

    // 취소 버튼 클릭 시
    $("#cancelBtn").click(function (){
        var subjectId = $("#subjectId").val();
        var referencId = $("#referenceId").val();
        location.href = "/subjects/" + subjectId + "/references/" + referencId;
    });

    // 파일 삭제 버튼 클릭 시 (이벤트 위임)
    $("#fileThumbnail").on("click", ".delete-button", function() {
        var filePreview = $(this).closest(".file-preview");
        var fileId = filePreview.find(".fileId").val();

        fileArr = fileArr.filter(function (fileIdValue){
           return fileIdValue !== fileId;
        });
        // 파일 미리보기에서 삭제
        filePreview.remove();
    });

    $.createFilePreview = function (file) {
        var filePreview = $('<div class="file-preview"></div>');
        var fileName = $("<span class='file-name'></span>").text(file.name);
        var deleteButton = $('<button class="btn btn-danger btn-sm delete-button">Delete</button>').click(function() {
            $.deleteFilePreview(filePreview, file);
        });

        filePreview.append(fileName);
        filePreview.append(deleteButton);

        return filePreview;
    }

    $.addFilePreview = function (file) {
        var fileThumbnail = $("#fileThumbnail");
        var filePreview = $.createFilePreview(file);

        fileThumbnail.append(filePreview);
    }

    $.deleteFilePreview = function (filePreview, file) {
        filePreview.remove();
        selectedFiles = selectedFiles.filter(function(f) {
            return f.name !== file.name;
        });
    }
});
