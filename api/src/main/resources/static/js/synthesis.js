const textToSpeak = "Hello, World!";
// const xhr = new XMLHttpRequest();
// xhr.open('POST', '/api/speak');
// xhr.setRequestHeader('Content-Type', 'text/plain');
// xhr.send(textToSpeak);

document.getElementById("test").onclick = function() {

  console.log(textToSpeak);

  $.ajax({
    url: "/api/speak",
    data: textToSpeak,
    contentType: "text/plain",
    type: "POST",
    success: function(result){
      console.log(result);
    }
  });
}
