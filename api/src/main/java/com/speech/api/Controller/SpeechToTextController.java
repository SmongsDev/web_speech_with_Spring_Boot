package com.speech.api.Controller;

import java.util.Base64;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.speech.api.models.SpeechPost;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.Transcript;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;

import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/speech")
public class SpeechToTextController {
@ResponseBody
  @RequestMapping("/transcribe")
  public String transcribe(SpeechPost post) throws Exception {
    //convert the base 64-encoded file to byte array
    byte[] byteArray = Base64.getDecoder().decode( post.getAudio() );
    
    //create a temporary file
    File tempFile = File.createTempFile("speech-", ".wav", null);
    //write to a temporary location
    FileOutputStream fos = new FileOutputStream(tempFile);
    fos.write(byteArray);
    //close the stream
    fos.close();
    
    //initiate the service
    SpeechToText service = new SpeechToText();
    //set the username and password
    service.setUsernameAndPassword( "ssm20213107@gs.cwnu.ac.kr", "Sinsongmin1!" );
    
    //build the recognize options.
    RecognizeOptions options = new RecognizeOptions.Builder()
            .contentType(HttpMediaType.AUDIO_WAV) //select your format
            .build();
    
    //execute the api service
    SpeechResults result = service.recognize(tempFile, options).execute();
    
    String text = null;
    //extract the transcript
    if( !result.getResults().isEmpty() ) {
      //load the list of transcript objects
      List<Transcript> transcripts = result.getResults();
      
      //iterate over the transcripts and select the one with final bool set
      for(Transcript transcript: transcripts){
        if(transcript.isFinal()){
          text = transcript.getAlternatives().get(0).getTranscript();
          break;
        }
      }
    }
    //return the transcript
    return text;
  }

  @RequestMapping("/test")
  public void testSpeech(){
    Authenticator authenticator = new IamAuthenticator("<iam_api_key>");
    SpeechToText service = new SpeechToText(authenticator);

    File audio = new File("src/test/resources/sample1.wav");

    RecognizeOptions options = new RecognizeOptions.Builder()
      .audio(audio)
      .contentType(HttpMediaType.AUDIO_WAV)
      .build();

    SpeechRecognitionResults transcript = service.recognize(options).execute().getResult();
    System.out.println(transcript);
  }
}