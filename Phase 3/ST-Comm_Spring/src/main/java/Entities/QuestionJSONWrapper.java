package Entities;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Ahmed Hussein
 *
 */
public class QuestionJSONWrapper extends PropertyEditorSupport{

	private Vector<Question> questions;
	
	public QuestionJSONWrapper(){
		questions = new Vector<Question>();
	}

	public Vector<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Vector<Question> questions) {
		this.questions = questions;
	}


	/**
	 * JSONString to be parsed and mapped to an object of {@link Entities.QuestionJSONWrapper}
	 */
	@Override
	public void setAsText(String JSONString) throws IllegalArgumentException {
		ObjectMapper mapper = new ObjectMapper();
		Vector<String> JSONQuestions = new Vector<String>();
		
		//the following pattern separates JSON questions
		Pattern p = Pattern.compile("[{]{1}[[^}]*[[[\"].*[\"]]|[[\'].*[\']]]?[^}]*]*[}]{1}");
		Matcher m = p.matcher(JSONString);

		while(m.find()){
			if(m.group().length() != 0)
                JSONQuestions.add(m.group());
		}
        Question question = null;
        Vector<String> choices = null;
        JsonNode JSONChoices;
        try {
        	for(int i=0; i<JSONQuestions.size(); i++){
        		question = new Question();
        		choices = new Vector<String>();
                JsonNode root = mapper.readTree(JSONQuestions.get(i));
                question.setCorrectAnswer(root.path("correctAnswer").asInt(-1)); //-1 is a default value
                question.setQuestionStatement(root.path("questionStatement").asText(""));
                question.setTime(Integer.parseInt(root.path("time").asText("")));
                JSONChoices = root.path("choices");
                for(int j=0; j<JSONChoices.size(); j++){
                	choices.add(JSONChoices.get(j).textValue());
                }
                question.setChoices(choices);
                questions.add(question);
        	}
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        setValue(this);
	}   
}