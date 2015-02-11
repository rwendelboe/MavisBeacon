import com.sun.deploy.util.ArrayUtil;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;


/**
 * Created by romanwendelboe on 10/23/14.
 */
public class MavisBeacon extends JFrame implements KeyListener, ActionListener{
    private final Dimension dimension = new Dimension(900, 700);

    private JMenuBar menuBar;
    private JMenu prompts, specialFeature;
    private JRadioButton prompt1, prompt2, prompt3, prompt4, prompt5, prompt6, prompt7, prompt8, prompt9, prompt10, prompt11;
    private JMenuItem wordsPerMinute;
    private ButtonGroup thePromptGroup;
    private JLabel intro, mostMissedCharacters, totalMissedChar, wordsPerMin, percentAccuracy, missedChar1, missedChar2, missedChar3, missedChar4, missedChar5;
    private JTextArea input, givenPrompt;
    private List<JButton> buttons = new LinkedList<JButton>();
    private List<JRadioButton> promptList = new LinkedList<JRadioButton>();

    private Map<Character, Integer> missedW;
    private int wrongIncrement = 1;
    private Timer timeForWPM = new Timer(1000, this);
    private double wordCount = 0;
    private double WPerM = 0;
    private int incrementPrompt = 0;
    private int counter = 0;
    private double totalAccuracy = 0;
    private double totalNumOfRightChar = 0;
    private int totalNumOfWrongChar = 0;
    private boolean promptSelected = false;
    private double seconds = 0;

    public MavisBeacon(){
        this.setLayout(null);

        addingContentsToGUI();

        addButtons();

        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();

        this.setSize(dimension);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e){

        timeForWPM.start();
        setThePromptText(e);

        //calc Words Per Min


        promptSelected = true;
        ++seconds;
       // myWatch.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

        if(promptSelected == false){
            return;
        }

        if(incrementPrompt >= promptList.size()){
            return;
        }else {
            input.append(e.getKeyChar() + "");
            input.setLineWrap(true);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean isValidKey = true;


        if(promptSelected == false){
            return;
        }
        if(incrementPrompt >= promptList.size()){
            return;
        }
       recognizeButtonsPressed(e, isValidKey);
    }
    @Override
    public void keyReleased(KeyEvent e) {

        recognizeButtonsReleased(e);
    }

    public void addButtons(){
        for (int i = 0; i < 41; ++i){
            if(i < 10) {
                buttons.add(new JButton(String.valueOf((char)('0' + i))));
                buttons.get(i).setSize(40, 40);
                buttons.get(i).setLocation(i* 40 + 150,480);

            }
            else if(i < 36) {
                buttons.add(new JButton(String.valueOf((char) ('a' + i - 10))));
                buttons.get(i).setSize(40, 40);
                buttons.get(i).setLocation(i * 40 - 290, 520);
                if (i >= 22) buttons.get(i).setLocation(i * 40 - 810, 560);
            }
            else if(i == 36) {
                buttons.add(new JButton("Shift"));
                buttons.get(i).setSize(80, 40);
                buttons.get(i).setLocation(i* 20 - 650, 600);
            }
            else if(i == 37) {
                buttons.add(new JButton(String.valueOf((char) ('A' - 26))));
                buttons.get(i).setSize(80, 40);
                buttons.get(i).setLocation(i * 20 - 590, 600);
            }
            else if(i == 38) {
                buttons.add(new JButton());
                buttons.get(i).setSize(240, 40);
                buttons.get(i).setLocation(i * 20 - 530, 600);

            }
            else if(i == 39) {
                buttons.add(new JButton(String.valueOf((char) ('A' - 19))));
                buttons.get(i).setSize(80, 40);
                buttons.get(i).setLocation(i * 20 - 310, 600);
            }
            else {
                buttons.add(new JButton(String.valueOf((char) ('A' - 21))));
                buttons.get(i).setSize(80, 40);
                buttons.get(i).setLocation(i * 20 - 250, 600);
            }
            buttons.get(i).setFocusable(false);
            this.getContentPane().add(buttons.get(i));
        }
    }

    public void addingContentsToGUI(){
        menuBar = new JMenuBar();
        menuBar.setSize(900, 20);

        prompts = new JMenu("Prompts");

        menuBar.add(prompts);

        prompt1 = new JRadioButton("Prompt 1");
        prompt2 = new JRadioButton("Prompt 2");
        prompt3 = new JRadioButton("Prompt 3");
        prompt4 = new JRadioButton("Prompt 4");
        prompt5 = new JRadioButton("Prompt 5");
        prompt6 = new JRadioButton("Prompt 6");
        prompt7 = new JRadioButton("Prompt 7");
        prompt8 = new JRadioButton("Prompt 8");
        prompt9 = new JRadioButton("Prompt 9");
        prompt10 = new JRadioButton("Prompt 10");
        prompt11 = new JRadioButton("Prompt 11");

        promptList.add(prompt1);
        promptList.add(prompt2);
        promptList.add(prompt3);
        promptList.add(prompt4);
        promptList.add(prompt5);
        promptList.add(prompt6);
        promptList.add(prompt7);
        promptList.add(prompt8);
        promptList.add(prompt9);
        promptList.add(prompt10);
        promptList.add(prompt11);

        thePromptGroup = new ButtonGroup();
        thePromptGroup.add(prompt1);
        thePromptGroup.add(prompt2);
        thePromptGroup.add(prompt3);
        thePromptGroup.add(prompt4);
        thePromptGroup.add(prompt5);
        thePromptGroup.add(prompt6);
        thePromptGroup.add(prompt7);
        thePromptGroup.add(prompt8);
        thePromptGroup.add(prompt9);
        thePromptGroup.add(prompt10);
        thePromptGroup.add(prompt11);

        prompts.add(prompt1);
        prompts.add(prompt2);
        prompts.add(prompt3);
        prompts.add(prompt4);
        prompts.add(prompt5);
        prompts.add(prompt6);
        prompts.add(prompt7);
        prompts.add(prompt8);
        prompts.add(prompt9);
        prompts.add(prompt10);
        prompts.add(prompt11);

        prompt1.addActionListener(this);
        prompt2.addActionListener(this);
        prompt3.addActionListener(this);
        prompt4.addActionListener(this);
        prompt5.addActionListener(this);
        prompt6.addActionListener(this);
        prompt7.addActionListener(this);
        prompt8.addActionListener(this);
        prompt9.addActionListener(this);
        prompt10.addActionListener(this);
        prompt11.addActionListener(this);

        this.getContentPane().add(menuBar);

        intro = new JLabel("Welcome! Lets do some typing!");
        intro.setSize(200, 20);
        intro.setLocation(250, 20);
        this.getContentPane().add(intro);

        input = new JTextArea();
        input.setSize(500, 150);
        input.setLocation(100, 260);
        input.setFocusable(false);
        this.getContentPane().add(input);

        givenPrompt = new JTextArea();
        givenPrompt.setSize(500, 100);
        givenPrompt.setLocation(100, 100);
        givenPrompt.setFocusable(false);
        this.getContentPane().add(givenPrompt);

        percentAccuracy = new JLabel();
        percentAccuracy.setSize(230, 20);
        percentAccuracy.setLocation(620, 260);
        this.getContentPane().add(percentAccuracy);

        totalMissedChar = new JLabel();
        totalMissedChar.setSize(230, 20);
        totalMissedChar.setLocation(620, 300);
        this.getContentPane().add(totalMissedChar);

        mostMissedCharacters = new JLabel("5 Most Missed: ");
        mostMissedCharacters.setSize(100, 20);
        mostMissedCharacters.setLocation(620, 340);
        mostMissedCharacters.setVisible(false);
        this.getContentPane().add(mostMissedCharacters);

        missedChar1 = new JLabel();
        missedChar1.setSize(20, 20);
        missedChar1.setLocation(740, 340);
        this.getContentPane().add(missedChar1);

        missedChar2 = new JLabel();
        missedChar2.setSize(20, 20);
        missedChar2.setLocation(750, 340);
        this.getContentPane().add(missedChar2);

        missedChar3 = new JLabel();
        missedChar3.setSize(20, 20);
        missedChar3.setLocation(770, 340);
        this.getContentPane().add(missedChar3);

        missedChar4 = new JLabel();
        missedChar4.setSize(20, 20);
        missedChar4.setLocation(790, 340);
        this.getContentPane().add(missedChar4);

        missedChar5 = new JLabel();
        missedChar5.setSize(20, 20);
        missedChar5.setLocation(810, 340);
        this.getContentPane().add(missedChar5);

        wordsPerMin = new JLabel();
        wordsPerMin.setSize(200, 20);
        wordsPerMin.setLocation(620, 380);
        this.getContentPane().add(wordsPerMin);

        input.setSize(500, 200);
        input.setLocation(100, 260);
        this.getContentPane().add(input);

        missedW = new HashMap<Character, Integer>();
    }

    public void setThePromptText(ActionEvent e){
        if(e.getSource().equals(prompt1)){
            givenPrompt.setText("A typed word is counted as any five keystrokes.");
            incrementPrompt = 0;
        }
        else if (e.getSource().equals(prompt2)){
            givenPrompt.setText("Do not stop to correct your errors in these first tests but check them out.");
            incrementPrompt = 1;
        }
        else if (e.getSource().equals(prompt3)){
            givenPrompt.setText("The beautiful scenic country of New Zealand is situated in the South Pacific to the east of Australia.");
            incrementPrompt = 2;
        }
        else if (e.getSource().equals(prompt4)){
            givenPrompt.setText("The ferry crosses Cook Strait and cruises beautiful Queen Charlotte Sounds between Wellington, NZ's Capital City, and Picton.");
            incrementPrompt = 3;
        }
        else if (e.getSource().equals(prompt5)){
            givenPrompt.setText("NZ's East Coast has many stretches of white sand and rolling surf which attract NZ and overseas surfers. They are popular NZ fmaily holiday places.");
            incrementPrompt = 4;
        }
        else if (e.getSource().equals(prompt6)){
            givenPrompt.setText("New Zealand is a land of contrasts, which attract thousands of overseas tourists every year to climb, ski or snowboard our mountains, swim, fish or cruise on our lakes and rivers.");
            incrementPrompt = 5;
        }
        else if (e.getSource().equals(prompt7)){
            givenPrompt.setText("Between The Southern Alps and the West Coast is a fantastic scenic drive taking the Haast Pass road. Here is our great rain forest. Most overseas and local tours include this route in their itinerary.");
            incrementPrompt = 6;
        }
        else if (e.getSource().equals(prompt8)){
            givenPrompt.setText("New Zealand is a very sport oriented country and sometimes hosts world events. Sports include tennis, golf, yachting, canoeing, cruising, cricket, soccer, rugby, basketball, netball, swimming, surf lifesaving, athletics, and riding.");
            incrementPrompt = 7;
        }
        else if (e.getSource().equals(prompt9)){
            givenPrompt.setText("Watching events where they take place is fine but many can only watch at home as the event is screened on our TVs. New Zealand is proud too of our sporting participants who have entered and gained medals in many sporting events including Olympic Games.");
            incrementPrompt = 8;
        }
        else if (e.getSource().equals(prompt10)){
            givenPrompt.setText("Masters' Games are very popular in New Zealand as in many other countries and NZ swimmers were really proud in the year 2002 to host the FINA World Masters Swimming Champs at Christchurch in the South Island, at which I gained 10th place medals for 100 m and 200 m backstroke.");
            incrementPrompt = 9;
        }
        else if (e.getSource().equals(prompt11)){
            givenPrompt.setText("Some challenging events which draw overseas sports people include the annual Coast to Coast. involving running, cycling and kayaking from the West Coast, through mountain passes to the East Coast, and the Iron Man including running, cycling, swimming. I am proud one of my sons twice took part in the Ironman.");
            incrementPrompt = 10;
        }
        givenPrompt.setLineWrap(true);
    }


    public void recognizeButtonsPressed(KeyEvent e, boolean isValidKey){

        if(e.isShiftDown()) {
            buttons.get(36).setSelected(true);
        }

        if(e.getKeyCode() > 47 && e.getKeyCode() < 58 ){
            buttons.get((int) (e.getKeyCode() - '0' )).setSelected(true);
        }
        else if (e.getKeyCode() == 222){
            buttons.get(37).setSelected(true);
        }
        else if (e.getKeyCode() == 32){
            buttons.get(38).setSelected(true);
        }
        else if (e.getKeyCode() == 46){
            buttons.get(39).setSelected(true);
        }
        else if (e.getKeyCode() == 44){
            buttons.get(40).setSelected(true);
        }
        else {
            if(e.getKeyCode()>55) {
                buttons.get((int) (e.getKeyCode()) - 55).setSelected(true);
            }
            else{
                    isValidKey = false;
            }
        }

        if(counter < givenPrompt.getText().length() && isValidKey) {
            if (givenPrompt.getText().charAt(counter++) == e.getKeyChar()) {
                totalNumOfRightChar++;
                System.out.println("Right");

            } else {
                int temp = 0;
                if(missedW.containsKey(e.getKeyChar())) {
                    int frequency = missedW.remove(e.getKeyChar());
                    missedW.put(e.getKeyChar(), frequency+1);
                }
                else {
                    missedW.put(e.getKeyChar(), wrongIncrement);
                }
                totalNumOfWrongChar++;
                System.out.println("Wrong");
            }
        }
        else if(isValidKey != false){
            ++incrementPrompt;

            if (incrementPrompt >= promptList.size()){

                long endTime = System.currentTimeMillis();
                List occurance = new ArrayList(missedW.keySet());
                Collections.sort(occurance);

                try{
                    mostMissedCharacters.setVisible(true);
                    missedChar1.setText("" + occurance.get(0));
                    missedChar2.setText("; " + occurance.get(1));
                    missedChar3.setText("; " + occurance.get(2));
                    missedChar4.setText("; " + occurance.get(3));
                    missedChar5.setText("; " + occurance.get(4));
                }
                catch (IndexOutOfBoundsException ex){

                }
                totalAccuracy = (totalNumOfRightChar / input.getText().length()) * 100;
                wordCount = (totalNumOfRightChar) /5 ;
                System.out.println("Word Count:" + wordCount);
                System.out.println("Time in Seconds:" + seconds);
                WPerM = wordCount/(seconds/60);

                percentAccuracy.setText("Percent Accuracy: " + String.format("%.0f", totalAccuracy) + "%");
                totalMissedChar.setText("Characters missed: " + totalNumOfWrongChar);
                wordsPerMin.setText("Your Words Per Min: " + String.format("%.0f", WPerM));

                return;
            }
            ActionEvent ae = new ActionEvent(promptList.get(incrementPrompt), 12, "");
            actionPerformed(ae);
            counter = 0;
        }

    }

    public void recognizeButtonsReleased(KeyEvent e){
        if (e.getKeyCode() > 47 && e.getKeyCode() < 58) {
            buttons.get((int) (e.getKeyCode() - '0')).setSelected(false);
        }

        else if (e.getKeyCode() == 16){
            buttons.get(36).setSelected(false);

        }
        else if (e.getKeyCode() == 222){
            buttons.get(37).setSelected(false);
        }
        else if (e.getKeyCode() == 32){
            buttons.get(38).setSelected(false);
        }
        else if (e.getKeyCode() == 46){
            buttons.get(39).setSelected(false);
        }
        else if (e.getKeyCode() == 44){
            buttons.get(40).setSelected(false);
        }
        else {
            if (e.getKeyCode() >= 55) {
                buttons.get((int) (e.getKeyCode()) - 55).setSelected(false);
            }
        }

    }


}
