$(document).ready(function (){
   $("#join").on("click", function (){
      location.href = "/auth/sign-up";
   });

   $("#login").on("click", function (){
      location.href = "/auth/login";
   });
});

// 초기 카운트 값을 설정합니다.
let like1Count = 1720;
let heart1Count = 8900;
let firecracker1Count = 1212;
let heart2Count = 898;
let trophy1Count = 1212;

document.getElementById('like1Button').addEventListener('click', function() {
   like1Count++;
   document.getElementById('like1Count').innerText = like1Count;
});

document.getElementById('heart1Button').addEventListener('click', function() {
   heart1Count++;
   document.getElementById('heart1Count').innerText = heart1Count;
});

document.getElementById('firecracker1Button').addEventListener('click', function() {
   firecracker1Count++;
   document.getElementById('firecracker1Count').innerText = firecracker1Count;
});

document.getElementById('heart2Button').addEventListener('click', function() {
   heart2Count++;
   document.getElementById('heart2Count').innerText = heart2Count;
});

document.getElementById('trophy1Button').addEventListener('click', function() {
   trophy1Count++;
   document.getElementById('trophy1Count').innerText = trophy1Count;
});