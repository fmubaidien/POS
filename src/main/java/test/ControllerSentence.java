package test;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerSentence {

    @RequestMapping(method= RequestMethod.POST, value = "/v1/sentence")
    public JSONObject index2(@RequestBody String preText) {
        StanfordCoreNLP stanfordCoreNLP = new StanfordCoreNLP("arabic");

        //String text = "أنا اسمي سارة. أنت ما اسمك";
        String text = preText;
        JSONObject obj = new JSONObject();

        JSONArray array = new JSONArray();

        CoreDocument coreDocument = new CoreDocument(text);

        stanfordCoreNLP.annotate(coreDocument);

        List<CoreSentence> sentences = coreDocument.sentences();

        int count=0;
        for(CoreSentence sentence: sentences){
            array.add(sentence.toString());
            //obj.put("Sentence:" + count++, sentence.toString());
        }
        obj.put("Sentence Splitter", array);
        return obj;
    }

}