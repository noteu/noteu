function copy_to_clipboard(subjectCodeId) {
    const copyCode = document.getElementById(subjectCodeId).value;
    navigator.clipboard.writeText(copyCode)
        .then(() => {
            alert('과목 코드 복사 완료!');
        })
        .catch((error) => {
            console.error('클립보드 복사 실패:', error);
        });
}