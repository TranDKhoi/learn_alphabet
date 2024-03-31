package com.learn_alphabet.activities.quiz.utils;

import com.learn_alphabet.R;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestionHandler {

	public static List<QuestionSet> questionSet=new ArrayList<QuestionSet>();
	
	public static void populateList(){
		questionSet.add(new QuestionSet(R.drawable.quiz_dog,"DOG",1, R.raw.an3));
    	questionSet.add(new QuestionSet(R.drawable.quiz_airplane,"AIRPLANE",2, R.raw.airplane));
    	questionSet.add(new QuestionSet(R.drawable.quiz_bike,"BIKE",3, R.raw.bike));
    	questionSet.add(new QuestionSet(R.drawable.quiz_camel,"CAMEL",4, R.raw.an10));
    	questionSet.add(new QuestionSet(R.drawable.quiz_cat,"CAT",5, R.raw.an2));
    	questionSet.add(new QuestionSet(R.drawable.quiz_chicken,"CHICKEN",6, R.raw.an5));
    	questionSet.add(new QuestionSet(R.drawable.quiz_cherry,"CHERRY",7, R.raw.f11));
    	questionSet.add(new QuestionSet(R.drawable.quiz_cow,"COW",8, R.raw.an1));
    	questionSet.add(new QuestionSet(R.drawable.quiz_circle,"CIRCLE",9, R.raw.circle));
    	questionSet.add(new QuestionSet(R.drawable.quiz_horse,"HORSE",10, R.raw.an12));
    	questionSet.add(new QuestionSet(R.drawable.quiz_color_black,"BLACK",11, R.raw.color_black));
    	questionSet.add(new QuestionSet(R.drawable.quiz_color_blue,"BLUE",12, R.raw.color_blue));
    	questionSet.add(new QuestionSet(R.drawable.quiz_donkey,"DONKEY",13, R.raw.an8));
    	questionSet.add(new QuestionSet(R.drawable.quiz_duck,"DUCK",14, R.raw.an9));

    	questionSet.add(new QuestionSet(R.drawable.quiz_crescent,"CRESCENT",15, R.raw.crescent));
    	questionSet.add(new QuestionSet(R.drawable.quiz_rooster,"ROOSTER",16, R.raw.an14));
    	questionSet.add(new QuestionSet(R.drawable.quiz_ears,"EARS",17, R.raw.ears));
    	questionSet.add(new QuestionSet(R.drawable.quiz_goat,"GOAT",18, R.raw.an7));
    	questionSet.add(new QuestionSet(R.drawable.quiz_diamond,"DIAMOND",19, R.raw.diamond));
    	questionSet.add(new QuestionSet(R.drawable.quiz_eight,"EIGHT",20, R.raw.a8));
    	questionSet.add(new QuestionSet(R.drawable.quiz_eyes,"EYES",21, R.raw.eyes));
    	questionSet.add(new QuestionSet(R.drawable.quiz_fig,"FIG",22, R.raw.f12));
    	questionSet.add(new QuestionSet(R.drawable.quiz_five,"FIVE",23, R.raw.a5));
    	questionSet.add(new QuestionSet(R.drawable.quiz_foot,"FOOT",24, R.raw.foot));
    	questionSet.add(new QuestionSet(R.drawable.quiz_four,"FOUR",25, R.raw.a4));
    	questionSet.add(new QuestionSet(R.drawable.quiz_grapes,"GRAPES",26, R.raw.f13));
    	questionSet.add(new QuestionSet(R.drawable.quiz_guava,"GUAVA",27, R.raw.f14));

    	questionSet.add(new QuestionSet(R.drawable.quiz_hand,"HAND",28, R.raw.hand));
    	questionSet.add(new QuestionSet(R.drawable.quiz_heart,"HEART",29, R.raw.heart));
    	questionSet.add(new QuestionSet(R.drawable.quiz_helicopter,"HELICOPTER",30, R.raw.helicopter));
    	questionSet.add(new QuestionSet(R.drawable.quiz_jeep,"JEEP",31, R.raw.jeep));
    	questionSet.add(new QuestionSet(R.drawable.quiz_rabbit,"RABBIT",32, R.raw.an4));
    	questionSet.add(new QuestionSet(R.drawable.quiz_mouth,"MOUTH",33, R.raw.mouth));
    	questionSet.add(new QuestionSet(R.drawable.quiz_nine,"NINE",34, R.raw.a9));
    	questionSet.add(new QuestionSet(R.drawable.quiz_sheep,"SHEEP",35, R.raw.an6));
    	questionSet.add(new QuestionSet(R.drawable.quiz_nose,"NOSE",36, R.raw.nose));
    	questionSet.add(new QuestionSet(R.drawable.quiz_color_yellow,"YELLOW",37, R.raw.color_yellow));
    	questionSet.add(new QuestionSet(R.drawable.quiz_turkey,"TURKEY",38, R.raw.an15));
    	questionSet.add(new QuestionSet(R.drawable.quiz_one,"ONE",39, R.raw.a1));
    	questionSet.add(new QuestionSet(R.drawable.quiz_papaya,"PAPAYA",40, R.raw.f17));

		questionSet.add(new QuestionSet(R.drawable.quiz_peache,"PEACH",41, R.raw.f18));
		questionSet.add(new QuestionSet(R.drawable.quiz_pear,"PEAR",42, R.raw.f20));
		questionSet.add(new QuestionSet(R.drawable.quiz_plum,"PLUM",43, R.raw.f19));
		questionSet.add(new QuestionSet(R.drawable.quiz_police,"POLICE",44, R.raw.police));
		questionSet.add(new QuestionSet(R.drawable.quiz_quince,"QUINCE",45, R.raw.f15));
		questionSet.add(new QuestionSet(R.drawable.quiz_rectangle,"RECTANGLE",46, R.raw.rectangle));
		questionSet.add(new QuestionSet(R.drawable.quiz_rocket,"ROCKET",47, R.raw.rocket));
		questionSet.add(new QuestionSet(R.drawable.quiz_seven,"SEVEN",48, R.raw.a7));
		questionSet.add(new QuestionSet(R.drawable.quiz_square,"SQUARE",49, R.raw.square));
		questionSet.add(new QuestionSet(R.drawable.quiz_six,"SIX",50, R.raw.a6));
		questionSet.add(new QuestionSet(R.drawable.quiz_star,"STAR",51, R.raw.star));
		questionSet.add(new QuestionSet(R.drawable.quiz_tank,"TANK",52, R.raw.tank));
		questionSet.add(new QuestionSet(R.drawable.quiz_taxi,"TAXI",53, R.raw.taxi));
		questionSet.add(new QuestionSet(R.drawable.quiz_ten,"TEN",54, R.raw.a10));
		questionSet.add(new QuestionSet(R.drawable.quiz_three,"THREE",55, R.raw.a3));
		questionSet.add(new QuestionSet(R.drawable.quiz_triangle,"TRIANGLE",56, R.raw.triangle));
		questionSet.add(new QuestionSet(R.drawable.quiz_two,"TWO",57, R.raw.a2));

	}
}
