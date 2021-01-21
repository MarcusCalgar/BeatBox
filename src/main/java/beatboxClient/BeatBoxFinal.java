package beatboxClient;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import javax.sound.midi.*;

/**
 *
 * @author Balázs
 */
public class BeatBoxFinal {

    private JFrame theFrame; //main frame of the gui
    private JPanel mainPanel; //main panel of the frame
    private JList incomingList; //where the incoming messages are stored and shown
    private JTextField userMessage; //where the user can write his/her own message
    private ArrayList<JCheckBox> checkboxList; //all the checkboxes
    private Vector<String> listVector = new Vector<>(); //good question, this needs to be replaced anyway
    private String userName; //name of the user
    private ObjectOutputStream out; 
    private ObjectInputStream in;
    private HashMap<String, boolean[]> otherSeqsMap = new HashMap<>(); //hashmap containing the music from others

    private Sequencer sequencer;
    private Sequence sequence;
    private Sequence mySequence = null;
    private Track track;

    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga",
        "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
    int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main(String[] args) {
        new BeatBoxFinal().startUp("Balazs");
    }

    public void startUp(String name) {
        userName = name;
        // open connection to the server        
        try {
            Socket sock = new Socket("127.0.0.1", 4242);            //the connection between the client and the server from the client side
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
            Thread remote = new Thread(new RemoteReader());
            remote.start();
        } catch (IOException ex) {
            System.out.println("Couldn't connect to server.");
        }
        setUpMidi();
        buildGUI();
    }

    public void buildGUI() {
        theFrame = new JFrame("Cyber BeatBox");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        checkboxList = new ArrayList<>();

        Box buttonBox = new Box(BoxLayout.Y_AXIS);
        JButton start = new JButton("Start");
        start.addActionListener(new StartListener());
        buttonBox.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(new StopListener());
        buttonBox.add(stop);

        JButton upTempo = new JButton("Tempo Up");
        upTempo.addActionListener(new UpTempoListener());
        buttonBox.add(upTempo);

        JButton downTempo = new JButton("Tempo Down");
        downTempo.addActionListener(new DownTempoListener());
        buttonBox.add(downTempo);

        JButton sendIt = new JButton("Send it");
        sendIt.addActionListener(new SendListener());
        buttonBox.add(sendIt);

        userMessage = new JTextField();
        buttonBox.add(userMessage);

        /*
        The list containing the incoming music is set to single selection. It's data is from the listVector variable
        */
        
        incomingList = new JList();
        incomingList.addListSelectionListener(new MyListSelectionListener());
        incomingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane theList = new JScrollPane(incomingList);
        buttonBox.add(theList);
        incomingList.setListData(listVector); // no data to start with

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i = 0; i < 16; i++) {
            nameBox.add(new Label(instrumentNames[i]));
        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        theFrame.getContentPane().add(background);
        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(1);
        grid.setHgap(2);

        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);

        for (int i = 0; i < 256; i++) {
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkboxList.add(c);
            mainPanel.add(c);
        } // end loop

        theFrame.setBounds(50, 50, 300, 300);
        theFrame.pack();
        theFrame.setVisible(true);

    }

    public void setUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer(); //The sequencer is the thing that plays the music. It sequences all the MIDI information into a 'song'.
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);   //The sequence is the actual song that will be played. A sequence has a Track, that holds the actual information.
            track = sequence.createTrack(); //The Track lives in the Sequence, and the MIDI data (Midi Events) lives in the Track.
            sequencer.setTempoInBPM(120);
        } catch (MidiUnavailableException | InvalidMidiDataException ex) {
        }
    }

    public void buildTrackAndStart() {
        ArrayList<Integer> trackList;       //an arraylist of integers that holds the instruments for each
        sequence.deleteTrack(track);                            // When start is pressed, it deletes whatever was stored before
        track = sequence.createTrack();                         // Creates a new track instead of the just deleted one

        for (int i = 0; i < 16; i++) {
            
            trackList = new ArrayList<>();
            
            for (int j = 0; j < 16; j++) {  //loops through all the checkboxes in the checkbox list
                JCheckBox jc = (JCheckBox) checkboxList.get(j + (16 * i));
                if (jc.isSelected()) {
                    int key = instruments[i]; //if the box is selected, defines an int that identifies the musical instrument
                    trackList.add(key); //adds the key to the arraylist
                } else {
                    trackList.add(null); // because this slot should be emptied to full 16 beats
                }
            } // close inner loop
            makeTracks(trackList); // creates a track from the arraylist for every instrument
        } // close outer loop
        
        track.add(makeEvent(192, 9, 1, 0, 15)); // so we always go to full 16 beats
        try {
            sequencer.setSequence(sequence);                        //adds the sequence to the sequencer
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);    //set continuos looping
            sequencer.start();                                      //starts the sequencer
            sequencer.setTempoInBPM(120);
        } catch (InvalidMidiDataException ex) {
        }
    } // close method

    public class StartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            buildTrackAndStart();
        }
    } //invokes the buildTrackAndStart() method

    public class StopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            sequencer.stop();
        }
    }

    public class UpTempoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor(tempoFactor * 1.03f);
        }
    }

    public class DownTempoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor(tempoFactor * 0.97f);
        }
    }

    public class SendListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // make an arraylist of just the STATE of the checkboxes
            boolean[] checkboxState = new boolean[256];
            for (int i = 0; i < 256; i++) {
                JCheckBox check = (JCheckBox) checkboxList.get(i);
                if (check.isSelected()) {
                    checkboxState[i] = true;
                }
            } // close loop
            //      String messageToSend = null;
            try {
                out.writeObject(userName + ": " + userMessage.getText());
                out.writeObject(checkboxState);
            } catch (IOException ex) {
                System.out.println("Sorry dude. Could not send it to the server");
            }
            userMessage.setText("");
        } // close actionPerformed

    }

    public class MyListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent le) {
            if (!le.getValueIsAdjusting()) {
                String selected = (String) incomingList.getSelectedValue();
                if (selected != null) {
                    // now go to the map and change the sequence
                    boolean[] selectedState = (boolean[]) otherSeqsMap.get(selected);
                    changeSequence(selectedState);
                    sequencer.stop();
                    buildTrackAndStart();
                }
            }
        } // close valueChanged
    } // close inner class

    public class RemoteReader implements Runnable {

        boolean[] checkboxState = null;
        String nameToShow = null;
        Object obj = null;

        @Override
        public void run() {
            try {
                while ((obj = in.readObject()) != null) {
                    System.out.println("Got an object from the server");
                    System.out.println(obj.getClass());
                    nameToShow = (String) obj;
                    checkboxState = (boolean[]) in.readObject();
                    otherSeqsMap.put(nameToShow, checkboxState);
                    listVector.add(nameToShow);
                    incomingList.setListData(listVector);
                } // close while
            } catch (ClassNotFoundException | IOException ex) {
            }
        } // close run
    } // close inner class

    public class PlayMineListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (mySequence != null) {
                sequence = mySequence; // restore to my original
            }
        }
    }

    public void changeSequence(boolean[] checkboxState) {
        for (int i = 0; i < 256; i++) {
            JCheckBox check = (JCheckBox) checkboxList.get(i);
            if (checkboxState[i]) {
                check.setSelected(true);
            } else {
                check.setSelected(false);
            }
        } // close loop
    }

    public void makeTracks(ArrayList list) {
        Iterator it = list.iterator();
        for (int i = 0; i < 16; i++) {
            Integer num = (Integer) it.next();
            if (num != null) {
                track.add(makeEvent(144, 9, num, 100, i));       // MidiEvent that starts playing the actual note. Play starts at time i.
                track.add(makeEvent(128, 9, num, 100, i + 1));   // MidiEvent that stops playing the actual note. Play stops at time i + 1.
            }
        } // close loop
    } // close makeTracks()

    /*
    A MidiEvent is actual music information, that is, which notes to play, how long, etc. A Midi instruction goes inside Messages.
    */
    public MidiEvent makeEvent(int messageType, int channel, int noteToPlay, int velocity, int whenToPlay) {
        //messageType is what to do. 144 is NoteOn, 122 is NoteOff
        //channel is like which instrument to play
        //velocity translates to loudness
        //whenToPlay is timing
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage(); // Creates a ShortMessage to hold the MidiEvent information.
            a.setMessage(messageType, channel, noteToPlay, velocity); // Put the instruction into the message
            event = new MidiEvent(a, whenToPlay); // Creates the MidiEvent
        } catch (InvalidMidiDataException ex) {
        }
        return event;
    } // close makeEvent()
}
