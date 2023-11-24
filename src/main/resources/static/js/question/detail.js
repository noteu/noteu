$(document).ready(function() {
    // SimpleMDE 초기화
    var contentElement = $("#content")[0];
    var simplemde = contentElement ? new SimpleMDE({element: contentElement}) : null;

    // SimpleMDE의 내용이 변경될 때마다 HTML로 변환하여 표시
    if (simplemde) {
        simplemde.codemirror.on("change", function() {
            var content = simplemde.markdown(simplemde.value());
            $("#contentPreview").html(content);
        });
    }
});