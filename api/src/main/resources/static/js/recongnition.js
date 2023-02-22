const recognition = new webkitSpeechRecognition();
recognition.continuous = true;
recognition.lang = 'en-US';
recognition.onresult = (event) => {
    const audioData = event.results[0][0].transcript;
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/transcribe');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onload = () => {
        const transcribedText = xhr.responseText;
        console.log(transcribedText);
    };
    xhr.send(JSON.stringify({audioData: audioData}));
};
recognition.start();