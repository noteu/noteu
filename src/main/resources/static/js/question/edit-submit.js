$(document).ready(function (){
    var simplemde = new SimpleMDE({element: $("#content")[0]});

    $("#submitBtn").click(function() {
        var subjectId = $("#subjectId").val();
        var questionId = $("#questionPostId").val();
        var title = $("#title").val();
        var content = simplemde.value();

        console.log(subjectId);
        console.log(questionId)
        console.log(title);
        console.log(content);

        $.ajax({
            url: "/subjects/" + subjectId + "/questions/edit/" + questionId,
            type: "POST",
            data: {"questionPostTitle": title, "questionPostContent":content},
            success:function(response){
                console.log("데이터 전송 성공");
                location.href = "/subjects/" + subjectId + "/questions";
            },
            error:function (error){
                console.log("error:" + error);
            }
        })
    });
});

