package beatboxClient;

import java.awt.Component;
import java.io.*;
import java.net.Socket;
import java.util.*;
import javax.sound.midi.*;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Balázs
 */
public class BeatBoxClient extends javax.swing.JFrame {

    private ArrayList<JCheckBox> checkboxList; //all the checkboxes
    private DefaultListModel nameList = new DefaultListModel();//Model that holds the chat data
    private String userName; //name of the user
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private HashMap<String, boolean[]> otherSeqsMap = new HashMap<>(); //hashmap containing the music from others
    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;
    int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public BeatBoxClient() {
        initComponents();
        getCheckBoxes();
        sortCheckBoxList();
        //Might add an enter name option later
        startUp("Balazs");
    }

    private void startUp(String name) {
        userName = name;
        // open connection to the server        
        try {
            Socket sock = new Socket("127.0.0.1", 4242);            //the connection between the client and the server from the client side
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
            Thread remote = new Thread(new RemoteReader());         //This thread is listening for incoming messages
            remote.start();
        } catch (IOException ex) {
            btSendRythm.setEnabled(false);  //No need to try to send anything if there isn't a server to send to.
            JOptionPane.showMessageDialog(rootPane, "Server not available.", "Server connection error.", JOptionPane.OK_OPTION);
        }
        setUpMidi();
    }

    private void setUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer(); //The sequencer is the thing that plays the music. It sequences all the MIDI information into a 'song'.
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);   //The sequence is the actual song that will be played. A sequence has a Track, that holds the actual information.
            track = sequence.createTrack(); //The Track lives in the Sequence, and the MIDI data (Midi Events) lives in the Track.
            sequencer.setTempoInBPM(120);
        } catch (MidiUnavailableException | InvalidMidiDataException ex) {
        }
    }

    /**
     * Sorts the chekboxes into order, so they will correspond with how they
     * appear on the screen.
     */
    private void sortCheckBoxList() {
        checkboxList.sort(new CheckBoxComparator());
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

    public class RemoteReader implements Runnable {

        boolean[] checkboxState = null;
        String nameToShow = null;
        Object obj = null;

        @Override
        public void run() {
            try {
                while ((obj = in.readObject()) != null) {
                    nameToShow = (String) obj;
                    checkboxState = (boolean[]) in.readObject();
                    otherSeqsMap.put(nameToShow, checkboxState);
                    nameList.addElement(nameToShow);
                    jlIncomingList.setModel(nameList);
                } // close while
            } catch (ClassNotFoundException | IOException ex) {
            }
        } // close run
    } // close inner class

    /**
     * The method collects all the components from the jpInstruments panel,
     * which contains the musical instrument labels and all the checkboxes.
     * Then, puts all the checkboxes into the checkboxList arraylist.
     */
    private void getCheckBoxes() {
        checkboxList = new ArrayList<>();
        Component[] components = jpInstruments.getComponents();
        for (Component component : components) {
            if (component.getClass().equals(JCheckBox.class)) {
                checkboxList.add((JCheckBox) component);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton6 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jpHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpInstruments = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        jCheckBox19 = new javax.swing.JCheckBox();
        jCheckBox20 = new javax.swing.JCheckBox();
        jCheckBox21 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox24 = new javax.swing.JCheckBox();
        jCheckBox25 = new javax.swing.JCheckBox();
        jCheckBox26 = new javax.swing.JCheckBox();
        jCheckBox27 = new javax.swing.JCheckBox();
        jCheckBox28 = new javax.swing.JCheckBox();
        jCheckBox29 = new javax.swing.JCheckBox();
        jCheckBox30 = new javax.swing.JCheckBox();
        jCheckBox31 = new javax.swing.JCheckBox();
        jCheckBox32 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox33 = new javax.swing.JCheckBox();
        jCheckBox34 = new javax.swing.JCheckBox();
        jCheckBox35 = new javax.swing.JCheckBox();
        jCheckBox36 = new javax.swing.JCheckBox();
        jCheckBox37 = new javax.swing.JCheckBox();
        jCheckBox38 = new javax.swing.JCheckBox();
        jCheckBox39 = new javax.swing.JCheckBox();
        jCheckBox40 = new javax.swing.JCheckBox();
        jCheckBox41 = new javax.swing.JCheckBox();
        jCheckBox42 = new javax.swing.JCheckBox();
        jCheckBox43 = new javax.swing.JCheckBox();
        jCheckBox44 = new javax.swing.JCheckBox();
        jCheckBox45 = new javax.swing.JCheckBox();
        jCheckBox46 = new javax.swing.JCheckBox();
        jCheckBox47 = new javax.swing.JCheckBox();
        jCheckBox48 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox49 = new javax.swing.JCheckBox();
        jCheckBox50 = new javax.swing.JCheckBox();
        jCheckBox51 = new javax.swing.JCheckBox();
        jCheckBox52 = new javax.swing.JCheckBox();
        jCheckBox53 = new javax.swing.JCheckBox();
        jCheckBox54 = new javax.swing.JCheckBox();
        jCheckBox55 = new javax.swing.JCheckBox();
        jCheckBox56 = new javax.swing.JCheckBox();
        jCheckBox57 = new javax.swing.JCheckBox();
        jCheckBox58 = new javax.swing.JCheckBox();
        jCheckBox59 = new javax.swing.JCheckBox();
        jCheckBox60 = new javax.swing.JCheckBox();
        jCheckBox61 = new javax.swing.JCheckBox();
        jCheckBox62 = new javax.swing.JCheckBox();
        jCheckBox63 = new javax.swing.JCheckBox();
        jCheckBox64 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox65 = new javax.swing.JCheckBox();
        jCheckBox66 = new javax.swing.JCheckBox();
        jCheckBox67 = new javax.swing.JCheckBox();
        jCheckBox68 = new javax.swing.JCheckBox();
        jCheckBox69 = new javax.swing.JCheckBox();
        jCheckBox70 = new javax.swing.JCheckBox();
        jCheckBox71 = new javax.swing.JCheckBox();
        jCheckBox72 = new javax.swing.JCheckBox();
        jCheckBox73 = new javax.swing.JCheckBox();
        jCheckBox74 = new javax.swing.JCheckBox();
        jCheckBox75 = new javax.swing.JCheckBox();
        jCheckBox76 = new javax.swing.JCheckBox();
        jCheckBox77 = new javax.swing.JCheckBox();
        jCheckBox78 = new javax.swing.JCheckBox();
        jCheckBox79 = new javax.swing.JCheckBox();
        jCheckBox80 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jCheckBox81 = new javax.swing.JCheckBox();
        jCheckBox82 = new javax.swing.JCheckBox();
        jCheckBox83 = new javax.swing.JCheckBox();
        jCheckBox84 = new javax.swing.JCheckBox();
        jCheckBox85 = new javax.swing.JCheckBox();
        jCheckBox86 = new javax.swing.JCheckBox();
        jCheckBox87 = new javax.swing.JCheckBox();
        jCheckBox88 = new javax.swing.JCheckBox();
        jCheckBox89 = new javax.swing.JCheckBox();
        jCheckBox90 = new javax.swing.JCheckBox();
        jCheckBox91 = new javax.swing.JCheckBox();
        jCheckBox92 = new javax.swing.JCheckBox();
        jCheckBox93 = new javax.swing.JCheckBox();
        jCheckBox94 = new javax.swing.JCheckBox();
        jCheckBox95 = new javax.swing.JCheckBox();
        jCheckBox96 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jCheckBox97 = new javax.swing.JCheckBox();
        jCheckBox98 = new javax.swing.JCheckBox();
        jCheckBox99 = new javax.swing.JCheckBox();
        jCheckBox100 = new javax.swing.JCheckBox();
        jCheckBox101 = new javax.swing.JCheckBox();
        jCheckBox102 = new javax.swing.JCheckBox();
        jCheckBox103 = new javax.swing.JCheckBox();
        jCheckBox104 = new javax.swing.JCheckBox();
        jCheckBox105 = new javax.swing.JCheckBox();
        jCheckBox106 = new javax.swing.JCheckBox();
        jCheckBox107 = new javax.swing.JCheckBox();
        jCheckBox108 = new javax.swing.JCheckBox();
        jCheckBox109 = new javax.swing.JCheckBox();
        jCheckBox110 = new javax.swing.JCheckBox();
        jCheckBox111 = new javax.swing.JCheckBox();
        jCheckBox112 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jCheckBox113 = new javax.swing.JCheckBox();
        jCheckBox114 = new javax.swing.JCheckBox();
        jCheckBox115 = new javax.swing.JCheckBox();
        jCheckBox116 = new javax.swing.JCheckBox();
        jCheckBox117 = new javax.swing.JCheckBox();
        jCheckBox118 = new javax.swing.JCheckBox();
        jCheckBox119 = new javax.swing.JCheckBox();
        jCheckBox120 = new javax.swing.JCheckBox();
        jCheckBox121 = new javax.swing.JCheckBox();
        jCheckBox122 = new javax.swing.JCheckBox();
        jCheckBox123 = new javax.swing.JCheckBox();
        jCheckBox124 = new javax.swing.JCheckBox();
        jCheckBox125 = new javax.swing.JCheckBox();
        jCheckBox126 = new javax.swing.JCheckBox();
        jCheckBox127 = new javax.swing.JCheckBox();
        jCheckBox128 = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jCheckBox129 = new javax.swing.JCheckBox();
        jCheckBox130 = new javax.swing.JCheckBox();
        jCheckBox131 = new javax.swing.JCheckBox();
        jCheckBox132 = new javax.swing.JCheckBox();
        jCheckBox133 = new javax.swing.JCheckBox();
        jCheckBox134 = new javax.swing.JCheckBox();
        jCheckBox135 = new javax.swing.JCheckBox();
        jCheckBox136 = new javax.swing.JCheckBox();
        jCheckBox137 = new javax.swing.JCheckBox();
        jCheckBox138 = new javax.swing.JCheckBox();
        jCheckBox139 = new javax.swing.JCheckBox();
        jCheckBox140 = new javax.swing.JCheckBox();
        jCheckBox141 = new javax.swing.JCheckBox();
        jCheckBox142 = new javax.swing.JCheckBox();
        jCheckBox143 = new javax.swing.JCheckBox();
        jCheckBox144 = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jCheckBox145 = new javax.swing.JCheckBox();
        jCheckBox146 = new javax.swing.JCheckBox();
        jCheckBox147 = new javax.swing.JCheckBox();
        jCheckBox148 = new javax.swing.JCheckBox();
        jCheckBox149 = new javax.swing.JCheckBox();
        jCheckBox150 = new javax.swing.JCheckBox();
        jCheckBox151 = new javax.swing.JCheckBox();
        jCheckBox152 = new javax.swing.JCheckBox();
        jCheckBox153 = new javax.swing.JCheckBox();
        jCheckBox154 = new javax.swing.JCheckBox();
        jCheckBox155 = new javax.swing.JCheckBox();
        jCheckBox156 = new javax.swing.JCheckBox();
        jCheckBox157 = new javax.swing.JCheckBox();
        jCheckBox158 = new javax.swing.JCheckBox();
        jCheckBox159 = new javax.swing.JCheckBox();
        jCheckBox160 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jCheckBox161 = new javax.swing.JCheckBox();
        jCheckBox162 = new javax.swing.JCheckBox();
        jCheckBox163 = new javax.swing.JCheckBox();
        jCheckBox164 = new javax.swing.JCheckBox();
        jCheckBox165 = new javax.swing.JCheckBox();
        jCheckBox166 = new javax.swing.JCheckBox();
        jCheckBox167 = new javax.swing.JCheckBox();
        jCheckBox168 = new javax.swing.JCheckBox();
        jCheckBox169 = new javax.swing.JCheckBox();
        jCheckBox170 = new javax.swing.JCheckBox();
        jCheckBox171 = new javax.swing.JCheckBox();
        jCheckBox172 = new javax.swing.JCheckBox();
        jCheckBox173 = new javax.swing.JCheckBox();
        jCheckBox174 = new javax.swing.JCheckBox();
        jCheckBox175 = new javax.swing.JCheckBox();
        jCheckBox176 = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jCheckBox177 = new javax.swing.JCheckBox();
        jCheckBox178 = new javax.swing.JCheckBox();
        jCheckBox179 = new javax.swing.JCheckBox();
        jCheckBox180 = new javax.swing.JCheckBox();
        jCheckBox181 = new javax.swing.JCheckBox();
        jCheckBox182 = new javax.swing.JCheckBox();
        jCheckBox183 = new javax.swing.JCheckBox();
        jCheckBox184 = new javax.swing.JCheckBox();
        jCheckBox185 = new javax.swing.JCheckBox();
        jCheckBox186 = new javax.swing.JCheckBox();
        jCheckBox187 = new javax.swing.JCheckBox();
        jCheckBox188 = new javax.swing.JCheckBox();
        jCheckBox189 = new javax.swing.JCheckBox();
        jCheckBox190 = new javax.swing.JCheckBox();
        jCheckBox191 = new javax.swing.JCheckBox();
        jCheckBox192 = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        jCheckBox193 = new javax.swing.JCheckBox();
        jCheckBox194 = new javax.swing.JCheckBox();
        jCheckBox195 = new javax.swing.JCheckBox();
        jCheckBox196 = new javax.swing.JCheckBox();
        jCheckBox197 = new javax.swing.JCheckBox();
        jCheckBox198 = new javax.swing.JCheckBox();
        jCheckBox199 = new javax.swing.JCheckBox();
        jCheckBox200 = new javax.swing.JCheckBox();
        jCheckBox201 = new javax.swing.JCheckBox();
        jCheckBox202 = new javax.swing.JCheckBox();
        jCheckBox203 = new javax.swing.JCheckBox();
        jCheckBox204 = new javax.swing.JCheckBox();
        jCheckBox205 = new javax.swing.JCheckBox();
        jCheckBox206 = new javax.swing.JCheckBox();
        jCheckBox207 = new javax.swing.JCheckBox();
        jCheckBox208 = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jCheckBox209 = new javax.swing.JCheckBox();
        jCheckBox210 = new javax.swing.JCheckBox();
        jCheckBox211 = new javax.swing.JCheckBox();
        jCheckBox212 = new javax.swing.JCheckBox();
        jCheckBox213 = new javax.swing.JCheckBox();
        jCheckBox214 = new javax.swing.JCheckBox();
        jCheckBox215 = new javax.swing.JCheckBox();
        jCheckBox216 = new javax.swing.JCheckBox();
        jCheckBox217 = new javax.swing.JCheckBox();
        jCheckBox218 = new javax.swing.JCheckBox();
        jCheckBox219 = new javax.swing.JCheckBox();
        jCheckBox220 = new javax.swing.JCheckBox();
        jCheckBox221 = new javax.swing.JCheckBox();
        jCheckBox222 = new javax.swing.JCheckBox();
        jCheckBox223 = new javax.swing.JCheckBox();
        jCheckBox224 = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();
        jCheckBox225 = new javax.swing.JCheckBox();
        jCheckBox226 = new javax.swing.JCheckBox();
        jCheckBox227 = new javax.swing.JCheckBox();
        jCheckBox228 = new javax.swing.JCheckBox();
        jCheckBox229 = new javax.swing.JCheckBox();
        jCheckBox230 = new javax.swing.JCheckBox();
        jCheckBox231 = new javax.swing.JCheckBox();
        jCheckBox232 = new javax.swing.JCheckBox();
        jCheckBox233 = new javax.swing.JCheckBox();
        jCheckBox234 = new javax.swing.JCheckBox();
        jCheckBox235 = new javax.swing.JCheckBox();
        jCheckBox236 = new javax.swing.JCheckBox();
        jCheckBox237 = new javax.swing.JCheckBox();
        jCheckBox238 = new javax.swing.JCheckBox();
        jCheckBox239 = new javax.swing.JCheckBox();
        jCheckBox240 = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        jCheckBox241 = new javax.swing.JCheckBox();
        jCheckBox242 = new javax.swing.JCheckBox();
        jCheckBox243 = new javax.swing.JCheckBox();
        jCheckBox244 = new javax.swing.JCheckBox();
        jCheckBox245 = new javax.swing.JCheckBox();
        jCheckBox246 = new javax.swing.JCheckBox();
        jCheckBox247 = new javax.swing.JCheckBox();
        jCheckBox248 = new javax.swing.JCheckBox();
        jCheckBox249 = new javax.swing.JCheckBox();
        jCheckBox250 = new javax.swing.JCheckBox();
        jCheckBox251 = new javax.swing.JCheckBox();
        jCheckBox252 = new javax.swing.JCheckBox();
        jCheckBox253 = new javax.swing.JCheckBox();
        jCheckBox254 = new javax.swing.JCheckBox();
        jCheckBox255 = new javax.swing.JCheckBox();
        jCheckBox256 = new javax.swing.JCheckBox();
        jpControls = new javax.swing.JPanel();
        btStart = new javax.swing.JButton();
        btStop = new javax.swing.JButton();
        btTempoUp = new javax.swing.JButton();
        btTempoDown = new javax.swing.JButton();
        btExit = new javax.swing.JButton();
        btClearInstruments = new javax.swing.JButton();
        jpChat = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlIncomingList = new javax.swing.JList<>();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tfUserMessage = new javax.swing.JTextField();
        btClearMessageField = new javax.swing.JButton();
        btSendRythm = new javax.swing.JButton();
        jpInstructions = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        jButton6.setText("jButton6");

        jLabel18.setText("jLabel18");

        jButton10.setText("jButton10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Super Beat Box");

        jpHeader.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hyper Super Best Beat Box");

        javax.swing.GroupLayout jpHeaderLayout = new javax.swing.GroupLayout(jpHeader);
        jpHeader.setLayout(jpHeaderLayout);
        jpHeaderLayout.setHorizontalGroup(
            jpHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpHeaderLayout.setVerticalGroup(
            jpHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpInstruments.setBorder(javax.swing.BorderFactory.createTitledBorder("Instruments"));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Bass Drum:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Closed Hi-Hat:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Open Hi-Hat:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Acoustic Snare:");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Crash Cymbal:");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Hand Clap:");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("High Tom:");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Hi Bongo:");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Maracas:");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Whistle:");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Low Conga:");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("Cowbell:");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("Vibraslap:");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel15.setText("Low-mid Tom:");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel16.setText("High Agogo:");

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel17.setText("Open Hi Conga:");

        javax.swing.GroupLayout jpInstrumentsLayout = new javax.swing.GroupLayout(jpInstruments);
        jpInstruments.setLayout(jpInstrumentsLayout);
        jpInstrumentsLayout.setHorizontalGroup(
            jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInstrumentsLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox225)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox226)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox227)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox228)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox229)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox230)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox231)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox232)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox233)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox234)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox235)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox236)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox237)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox238)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox239)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox240))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox64))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox71)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox74)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox75)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox76)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox77)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox79)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox81)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox82)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox83)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox84)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox86)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox87)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox88)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox89)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox90)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox91)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox92)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox93)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox94)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox95)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox96))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox97)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox98)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox99)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox100)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox101)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox102)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox103)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox104)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox105)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox106)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox107)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox108)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox109)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox110)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox111)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox112))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox113)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox114)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox115)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox116)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox117)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox118)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox119)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox120)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox121)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox122)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox123)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox124)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox125)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox126)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox127)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox128))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox129)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox130)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox131)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox132)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox133)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox134)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox135)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox136)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox137)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox138)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox139)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox140)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox141)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox142)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox143)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox144))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox145)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox146)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox147)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox148)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox149)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox151)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox152)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox153)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox154)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox155)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox156)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox157)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox158)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox159)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox160))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox161)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox162)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox163)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox164)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox165)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox166)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox167)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox168)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox169)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox170)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox171)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox172)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox173)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox174)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox175)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox176))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox177)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox178)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox179)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox180)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox181)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox182)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox183)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox184)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox185)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox186)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox187)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox188)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox189)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox190)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox191)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox192))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox193)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox194)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox195)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox196)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox197)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox198)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox199)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox200)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox201)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox202)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox203)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox204)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox205)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox206)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox207)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox208))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox209)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox210)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox211)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox212)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox213)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox214)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox215)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox216)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox217)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox218)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox219)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox220)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox221)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox222)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox223)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox224))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInstrumentsLayout.createSequentialGroup()
                        .addComponent(jCheckBox241)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox242)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox243)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox244)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox245)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox246)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox247)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox248)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox249)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox250)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox251)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox252)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox253)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox254)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox255)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox256)))
                .addContainerGap())
        );

        jpInstrumentsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel13, jLabel14, jLabel15, jLabel16, jLabel17, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9});

        jpInstrumentsLayout.setVerticalGroup(
            jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInstrumentsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox3)
                        .addComponent(jCheckBox4)
                        .addComponent(jCheckBox5)
                        .addComponent(jCheckBox6))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox1)
                        .addComponent(jCheckBox2)
                        .addComponent(jCheckBox7)
                        .addComponent(jCheckBox8))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox11)
                        .addComponent(jCheckBox12)
                        .addComponent(jCheckBox13)
                        .addComponent(jCheckBox14))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox9)
                        .addComponent(jCheckBox10)
                        .addComponent(jCheckBox15)
                        .addComponent(jCheckBox16))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox19)
                        .addComponent(jCheckBox20)
                        .addComponent(jCheckBox21)
                        .addComponent(jCheckBox22))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox17)
                        .addComponent(jCheckBox18)
                        .addComponent(jCheckBox23)
                        .addComponent(jCheckBox24))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox27)
                        .addComponent(jCheckBox28)
                        .addComponent(jCheckBox29)
                        .addComponent(jCheckBox30))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox25)
                        .addComponent(jCheckBox26)
                        .addComponent(jCheckBox31)
                        .addComponent(jCheckBox32))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox35)
                        .addComponent(jCheckBox36)
                        .addComponent(jCheckBox37)
                        .addComponent(jCheckBox38))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox33)
                        .addComponent(jCheckBox34)
                        .addComponent(jCheckBox39)
                        .addComponent(jCheckBox40))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox43)
                        .addComponent(jCheckBox44)
                        .addComponent(jCheckBox45)
                        .addComponent(jCheckBox46))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox41)
                        .addComponent(jCheckBox42)
                        .addComponent(jCheckBox47)
                        .addComponent(jCheckBox48))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox51)
                        .addComponent(jCheckBox52)
                        .addComponent(jCheckBox53)
                        .addComponent(jCheckBox54))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox49)
                        .addComponent(jCheckBox50)
                        .addComponent(jCheckBox55)
                        .addComponent(jCheckBox56))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox59)
                        .addComponent(jCheckBox60)
                        .addComponent(jCheckBox61)
                        .addComponent(jCheckBox62))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox57)
                        .addComponent(jCheckBox58)
                        .addComponent(jCheckBox63)
                        .addComponent(jCheckBox64))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox67)
                        .addComponent(jCheckBox68)
                        .addComponent(jCheckBox69)
                        .addComponent(jCheckBox70))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox65)
                        .addComponent(jCheckBox66)
                        .addComponent(jCheckBox71)
                        .addComponent(jCheckBox72))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox75)
                        .addComponent(jCheckBox76)
                        .addComponent(jCheckBox77)
                        .addComponent(jCheckBox78))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox73)
                        .addComponent(jCheckBox74)
                        .addComponent(jCheckBox79)
                        .addComponent(jCheckBox80))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox83)
                        .addComponent(jCheckBox84)
                        .addComponent(jCheckBox85)
                        .addComponent(jCheckBox86))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox81)
                        .addComponent(jCheckBox82)
                        .addComponent(jCheckBox87)
                        .addComponent(jCheckBox88))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox91)
                        .addComponent(jCheckBox92)
                        .addComponent(jCheckBox93)
                        .addComponent(jCheckBox94))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox89)
                        .addComponent(jCheckBox90)
                        .addComponent(jCheckBox95)
                        .addComponent(jCheckBox96))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox99)
                        .addComponent(jCheckBox100)
                        .addComponent(jCheckBox101)
                        .addComponent(jCheckBox102))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox97)
                        .addComponent(jCheckBox98)
                        .addComponent(jCheckBox103)
                        .addComponent(jCheckBox104))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox107)
                        .addComponent(jCheckBox108)
                        .addComponent(jCheckBox109)
                        .addComponent(jCheckBox110))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox105)
                        .addComponent(jCheckBox106)
                        .addComponent(jCheckBox111)
                        .addComponent(jCheckBox112))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox115)
                        .addComponent(jCheckBox116)
                        .addComponent(jCheckBox117)
                        .addComponent(jCheckBox118))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox113)
                        .addComponent(jCheckBox114)
                        .addComponent(jCheckBox119)
                        .addComponent(jCheckBox120))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox123)
                        .addComponent(jCheckBox124)
                        .addComponent(jCheckBox125)
                        .addComponent(jCheckBox126))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox121)
                        .addComponent(jCheckBox122)
                        .addComponent(jCheckBox127)
                        .addComponent(jCheckBox128))
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox131)
                        .addComponent(jCheckBox132)
                        .addComponent(jCheckBox133)
                        .addComponent(jCheckBox134))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox129)
                        .addComponent(jCheckBox130)
                        .addComponent(jCheckBox135)
                        .addComponent(jCheckBox136))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox139)
                        .addComponent(jCheckBox140)
                        .addComponent(jCheckBox141)
                        .addComponent(jCheckBox142))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox137)
                        .addComponent(jCheckBox138)
                        .addComponent(jCheckBox143)
                        .addComponent(jCheckBox144))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox147)
                        .addComponent(jCheckBox148)
                        .addComponent(jCheckBox149)
                        .addComponent(jCheckBox150))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox145)
                        .addComponent(jCheckBox146)
                        .addComponent(jCheckBox151)
                        .addComponent(jCheckBox152))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox155)
                        .addComponent(jCheckBox156)
                        .addComponent(jCheckBox157)
                        .addComponent(jCheckBox158))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox153)
                        .addComponent(jCheckBox154)
                        .addComponent(jCheckBox159)
                        .addComponent(jCheckBox160))
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox163)
                        .addComponent(jCheckBox164)
                        .addComponent(jCheckBox165)
                        .addComponent(jCheckBox166))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox161)
                        .addComponent(jCheckBox162)
                        .addComponent(jCheckBox167)
                        .addComponent(jCheckBox168))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox171)
                        .addComponent(jCheckBox172)
                        .addComponent(jCheckBox173)
                        .addComponent(jCheckBox174))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox169)
                        .addComponent(jCheckBox170)
                        .addComponent(jCheckBox175)
                        .addComponent(jCheckBox176))
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox179)
                        .addComponent(jCheckBox180)
                        .addComponent(jCheckBox181)
                        .addComponent(jCheckBox182))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox177)
                        .addComponent(jCheckBox178)
                        .addComponent(jCheckBox183)
                        .addComponent(jCheckBox184))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox187)
                        .addComponent(jCheckBox188)
                        .addComponent(jCheckBox189)
                        .addComponent(jCheckBox190))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox185)
                        .addComponent(jCheckBox186)
                        .addComponent(jCheckBox191)
                        .addComponent(jCheckBox192))
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox195)
                        .addComponent(jCheckBox196)
                        .addComponent(jCheckBox197)
                        .addComponent(jCheckBox198))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox193)
                        .addComponent(jCheckBox194)
                        .addComponent(jCheckBox199)
                        .addComponent(jCheckBox200))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox203)
                        .addComponent(jCheckBox204)
                        .addComponent(jCheckBox205)
                        .addComponent(jCheckBox206))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox201)
                        .addComponent(jCheckBox202)
                        .addComponent(jCheckBox207)
                        .addComponent(jCheckBox208))
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox211)
                        .addComponent(jCheckBox212)
                        .addComponent(jCheckBox213)
                        .addComponent(jCheckBox214))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox209)
                        .addComponent(jCheckBox210)
                        .addComponent(jCheckBox215)
                        .addComponent(jCheckBox216))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox219)
                        .addComponent(jCheckBox220)
                        .addComponent(jCheckBox221)
                        .addComponent(jCheckBox222))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox217)
                        .addComponent(jCheckBox218)
                        .addComponent(jCheckBox223)
                        .addComponent(jCheckBox224))
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox227)
                        .addComponent(jCheckBox228)
                        .addComponent(jCheckBox229)
                        .addComponent(jCheckBox230))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox225)
                        .addComponent(jCheckBox226)
                        .addComponent(jCheckBox231)
                        .addComponent(jCheckBox232))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox235)
                        .addComponent(jCheckBox236)
                        .addComponent(jCheckBox237)
                        .addComponent(jCheckBox238))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox233)
                        .addComponent(jCheckBox234)
                        .addComponent(jCheckBox239)
                        .addComponent(jCheckBox240))
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox243)
                        .addComponent(jCheckBox244)
                        .addComponent(jCheckBox245)
                        .addComponent(jCheckBox246))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox241)
                        .addComponent(jCheckBox242)
                        .addComponent(jCheckBox247)
                        .addComponent(jCheckBox248))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox251)
                        .addComponent(jCheckBox252)
                        .addComponent(jCheckBox253)
                        .addComponent(jCheckBox254))
                    .addGroup(jpInstrumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox249)
                        .addComponent(jCheckBox250)
                        .addComponent(jCheckBox255)
                        .addComponent(jCheckBox256))
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpInstrumentsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jCheckBox10, jCheckBox15, jCheckBox16, jCheckBox9, jLabel2});

        jpInstrumentsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jCheckBox17, jLabel3});

        jpInstrumentsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jCheckBox33, jLabel4});

        jpInstrumentsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jCheckBox49, jLabel10, jLabel11, jLabel12, jLabel13, jLabel14, jLabel15, jLabel16, jLabel17, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9});

        jpControls.setBorder(javax.swing.BorderFactory.createTitledBorder("Controls"));

        btStart.setText("Start");
        btStart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartActionPerformed(evt);
            }
        });

        btStop.setText("Stop");
        btStop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStopActionPerformed(evt);
            }
        });

        btTempoUp.setText("Tempo Up");
        btTempoUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btTempoUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTempoUpActionPerformed(evt);
            }
        });

        btTempoDown.setText("Tempo Down");
        btTempoDown.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btTempoDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTempoDownActionPerformed(evt);
            }
        });

        btExit.setText("Exit");
        btExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExitActionPerformed(evt);
            }
        });

        btClearInstruments.setText("Clear Checkboxes");
        btClearInstruments.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btClearInstruments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearInstrumentsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpControlsLayout = new javax.swing.GroupLayout(jpControls);
        jpControls.setLayout(jpControlsLayout);
        jpControlsLayout.setHorizontalGroup(
            jpControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpControlsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpControlsLayout.createSequentialGroup()
                        .addComponent(btStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btStop))
                    .addGroup(jpControlsLayout.createSequentialGroup()
                        .addComponent(btTempoUp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btTempoDown))
                    .addGroup(jpControlsLayout.createSequentialGroup()
                        .addComponent(btClearInstruments, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btExit)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jpControlsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btClearInstruments, btExit, btStart, btStop, btTempoDown, btTempoUp});

        jpControlsLayout.setVerticalGroup(
            jpControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpControlsLayout.createSequentialGroup()
                .addGroup(jpControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btStart)
                    .addComponent(btStop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btTempoUp)
                    .addComponent(btTempoDown))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btExit)
                    .addComponent(btClearInstruments)))
        );

        jpChat.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat"));

        jlIncomingList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlIncomingListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jlIncomingList);

        jLabel19.setText("Your message:");

        jScrollPane3.setViewportView(tfUserMessage);

        btClearMessageField.setText("Clear Message");
        btClearMessageField.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btClearMessageField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearMessageFieldActionPerformed(evt);
            }
        });

        btSendRythm.setText("Send Rythm & Message");
        btSendRythm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btSendRythm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSendRythmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpChatLayout = new javax.swing.GroupLayout(jpChat);
        jpChat.setLayout(jpChatLayout);
        jpChatLayout.setHorizontalGroup(
            jpChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane3)
            .addGroup(jpChatLayout.createSequentialGroup()
                .addComponent(jLabel19)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jpChatLayout.createSequentialGroup()
                .addComponent(btSendRythm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btClearMessageField))
        );

        jpChatLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btClearMessageField, btSendRythm});

        jpChatLayout.setVerticalGroup(
            jpChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpChatLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btClearMessageField)
                    .addComponent(btSendRythm)))
        );

        jpInstructions.setBorder(javax.swing.BorderFactory.createTitledBorder("How to Use"));

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("- Select the checkboxes with the \n  instruments you want to use.\n\n- Click Start to start the music, \n  click Stop to end.\n\n- Click Tempo Up / Down to\n  modify the speed of your tune.\n\n- Click Send Rythm to share your\n  music with the others in the chat.\n (Only if server connection is working.)\n\n- Click Exit to quit.\n  (Shocking, I know.)");
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jpInstructionsLayout = new javax.swing.GroupLayout(jpInstructions);
        jpInstructions.setLayout(jpInstructionsLayout);
        jpInstructionsLayout.setHorizontalGroup(
            jpInstructionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jpInstructionsLayout.setVerticalGroup(
            jpInstructionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpInstruments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpControls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpInstructions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jpHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpInstructions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpInstruments, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartActionPerformed
        buildTrackAndStart();
    }//GEN-LAST:event_btStartActionPerformed

    private void btStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStopActionPerformed
        sequencer.stop();
    }//GEN-LAST:event_btStopActionPerformed

    private void btTempoUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTempoUpActionPerformed
        float tempoFactor = sequencer.getTempoFactor();
        sequencer.setTempoFactor(tempoFactor * 1.03f);
    }//GEN-LAST:event_btTempoUpActionPerformed

    private void btTempoDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTempoDownActionPerformed
        float tempoFactor = sequencer.getTempoFactor();
        sequencer.setTempoFactor(tempoFactor * 0.97f);
    }//GEN-LAST:event_btTempoDownActionPerformed

    private void btSendRythmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSendRythmActionPerformed
        // make an array of just the STATE of the checkboxes and write it to the server
        boolean[] checkboxState = new boolean[256];
        for (int i = 0; i < 256; i++) {
            JCheckBox check = (JCheckBox) checkboxList.get(i);
            if (check.isSelected()) {
                checkboxState[i] = true;
            }
        } // close loop
        try {
            out.writeObject(userName + ": " + tfUserMessage.getText());
            out.writeObject(checkboxState);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Server connection error.", JOptionPane.OK_OPTION);
        }
        tfUserMessage.setText("");
    }//GEN-LAST:event_btSendRythmActionPerformed

    private void btExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExitActionPerformed
        try {
            sequencer.close();
            in.close();
            out.close();
            System.exit(0);
        } catch (IOException ex) {
        } finally {
            System.exit(0);
        }
    }//GEN-LAST:event_btExitActionPerformed

    private void jlIncomingListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlIncomingListValueChanged
        if (!evt.getValueIsAdjusting()) {
                String selected = (String) jlIncomingList.getSelectedValue();
                if (selected != null) {
                    // now go to the map and change the sequence
                    boolean[] selectedState = (boolean[]) otherSeqsMap.get(selected);
                    changeSequence(selectedState);
                    sequencer.stop();
                    buildTrackAndStart();
                }
            }
    }//GEN-LAST:event_jlIncomingListValueChanged

    private void btClearMessageFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearMessageFieldActionPerformed
        tfUserMessage.setText("");
    }//GEN-LAST:event_btClearMessageFieldActionPerformed

    private void btClearInstrumentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearInstrumentsActionPerformed
        checkboxList.stream().forEach(checkbox -> {
            checkbox.setSelected(false);
        });
    }//GEN-LAST:event_btClearInstrumentsActionPerformed

    public void buildTrackAndStart() {
        ArrayList<Integer> trackList;       //an arraylist of integers that holds the 'list of tracks'
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

        track.add(makeEvent(192, 9, 1, 0, 15)); //So we always go to full 16 beats. 192 stands for Program change for changing default instrument.
        try {
            sequencer.setSequence(sequence);                        //adds the sequence to the sequencer
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);    //set continuos looping
            sequencer.start();                                      //starts the sequencer
            sequencer.setTempoInBPM(120);
        } catch (InvalidMidiDataException ex) {
        }
    } // close method

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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BeatBoxClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BeatBoxClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BeatBoxClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BeatBoxClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BeatBoxClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClearInstruments;
    private javax.swing.JButton btClearMessageField;
    private javax.swing.JButton btExit;
    private javax.swing.JButton btSendRythm;
    private javax.swing.JButton btStart;
    private javax.swing.JButton btStop;
    private javax.swing.JButton btTempoDown;
    private javax.swing.JButton btTempoUp;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox100;
    private javax.swing.JCheckBox jCheckBox101;
    private javax.swing.JCheckBox jCheckBox102;
    private javax.swing.JCheckBox jCheckBox103;
    private javax.swing.JCheckBox jCheckBox104;
    private javax.swing.JCheckBox jCheckBox105;
    private javax.swing.JCheckBox jCheckBox106;
    private javax.swing.JCheckBox jCheckBox107;
    private javax.swing.JCheckBox jCheckBox108;
    private javax.swing.JCheckBox jCheckBox109;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox110;
    private javax.swing.JCheckBox jCheckBox111;
    private javax.swing.JCheckBox jCheckBox112;
    private javax.swing.JCheckBox jCheckBox113;
    private javax.swing.JCheckBox jCheckBox114;
    private javax.swing.JCheckBox jCheckBox115;
    private javax.swing.JCheckBox jCheckBox116;
    private javax.swing.JCheckBox jCheckBox117;
    private javax.swing.JCheckBox jCheckBox118;
    private javax.swing.JCheckBox jCheckBox119;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox120;
    private javax.swing.JCheckBox jCheckBox121;
    private javax.swing.JCheckBox jCheckBox122;
    private javax.swing.JCheckBox jCheckBox123;
    private javax.swing.JCheckBox jCheckBox124;
    private javax.swing.JCheckBox jCheckBox125;
    private javax.swing.JCheckBox jCheckBox126;
    private javax.swing.JCheckBox jCheckBox127;
    private javax.swing.JCheckBox jCheckBox128;
    private javax.swing.JCheckBox jCheckBox129;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox130;
    private javax.swing.JCheckBox jCheckBox131;
    private javax.swing.JCheckBox jCheckBox132;
    private javax.swing.JCheckBox jCheckBox133;
    private javax.swing.JCheckBox jCheckBox134;
    private javax.swing.JCheckBox jCheckBox135;
    private javax.swing.JCheckBox jCheckBox136;
    private javax.swing.JCheckBox jCheckBox137;
    private javax.swing.JCheckBox jCheckBox138;
    private javax.swing.JCheckBox jCheckBox139;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox140;
    private javax.swing.JCheckBox jCheckBox141;
    private javax.swing.JCheckBox jCheckBox142;
    private javax.swing.JCheckBox jCheckBox143;
    private javax.swing.JCheckBox jCheckBox144;
    private javax.swing.JCheckBox jCheckBox145;
    private javax.swing.JCheckBox jCheckBox146;
    private javax.swing.JCheckBox jCheckBox147;
    private javax.swing.JCheckBox jCheckBox148;
    private javax.swing.JCheckBox jCheckBox149;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox150;
    private javax.swing.JCheckBox jCheckBox151;
    private javax.swing.JCheckBox jCheckBox152;
    private javax.swing.JCheckBox jCheckBox153;
    private javax.swing.JCheckBox jCheckBox154;
    private javax.swing.JCheckBox jCheckBox155;
    private javax.swing.JCheckBox jCheckBox156;
    private javax.swing.JCheckBox jCheckBox157;
    private javax.swing.JCheckBox jCheckBox158;
    private javax.swing.JCheckBox jCheckBox159;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox160;
    private javax.swing.JCheckBox jCheckBox161;
    private javax.swing.JCheckBox jCheckBox162;
    private javax.swing.JCheckBox jCheckBox163;
    private javax.swing.JCheckBox jCheckBox164;
    private javax.swing.JCheckBox jCheckBox165;
    private javax.swing.JCheckBox jCheckBox166;
    private javax.swing.JCheckBox jCheckBox167;
    private javax.swing.JCheckBox jCheckBox168;
    private javax.swing.JCheckBox jCheckBox169;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox170;
    private javax.swing.JCheckBox jCheckBox171;
    private javax.swing.JCheckBox jCheckBox172;
    private javax.swing.JCheckBox jCheckBox173;
    private javax.swing.JCheckBox jCheckBox174;
    private javax.swing.JCheckBox jCheckBox175;
    private javax.swing.JCheckBox jCheckBox176;
    private javax.swing.JCheckBox jCheckBox177;
    private javax.swing.JCheckBox jCheckBox178;
    private javax.swing.JCheckBox jCheckBox179;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox180;
    private javax.swing.JCheckBox jCheckBox181;
    private javax.swing.JCheckBox jCheckBox182;
    private javax.swing.JCheckBox jCheckBox183;
    private javax.swing.JCheckBox jCheckBox184;
    private javax.swing.JCheckBox jCheckBox185;
    private javax.swing.JCheckBox jCheckBox186;
    private javax.swing.JCheckBox jCheckBox187;
    private javax.swing.JCheckBox jCheckBox188;
    private javax.swing.JCheckBox jCheckBox189;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox190;
    private javax.swing.JCheckBox jCheckBox191;
    private javax.swing.JCheckBox jCheckBox192;
    private javax.swing.JCheckBox jCheckBox193;
    private javax.swing.JCheckBox jCheckBox194;
    private javax.swing.JCheckBox jCheckBox195;
    private javax.swing.JCheckBox jCheckBox196;
    private javax.swing.JCheckBox jCheckBox197;
    private javax.swing.JCheckBox jCheckBox198;
    private javax.swing.JCheckBox jCheckBox199;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox20;
    private javax.swing.JCheckBox jCheckBox200;
    private javax.swing.JCheckBox jCheckBox201;
    private javax.swing.JCheckBox jCheckBox202;
    private javax.swing.JCheckBox jCheckBox203;
    private javax.swing.JCheckBox jCheckBox204;
    private javax.swing.JCheckBox jCheckBox205;
    private javax.swing.JCheckBox jCheckBox206;
    private javax.swing.JCheckBox jCheckBox207;
    private javax.swing.JCheckBox jCheckBox208;
    private javax.swing.JCheckBox jCheckBox209;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox210;
    private javax.swing.JCheckBox jCheckBox211;
    private javax.swing.JCheckBox jCheckBox212;
    private javax.swing.JCheckBox jCheckBox213;
    private javax.swing.JCheckBox jCheckBox214;
    private javax.swing.JCheckBox jCheckBox215;
    private javax.swing.JCheckBox jCheckBox216;
    private javax.swing.JCheckBox jCheckBox217;
    private javax.swing.JCheckBox jCheckBox218;
    private javax.swing.JCheckBox jCheckBox219;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox220;
    private javax.swing.JCheckBox jCheckBox221;
    private javax.swing.JCheckBox jCheckBox222;
    private javax.swing.JCheckBox jCheckBox223;
    private javax.swing.JCheckBox jCheckBox224;
    private javax.swing.JCheckBox jCheckBox225;
    private javax.swing.JCheckBox jCheckBox226;
    private javax.swing.JCheckBox jCheckBox227;
    private javax.swing.JCheckBox jCheckBox228;
    private javax.swing.JCheckBox jCheckBox229;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox230;
    private javax.swing.JCheckBox jCheckBox231;
    private javax.swing.JCheckBox jCheckBox232;
    private javax.swing.JCheckBox jCheckBox233;
    private javax.swing.JCheckBox jCheckBox234;
    private javax.swing.JCheckBox jCheckBox235;
    private javax.swing.JCheckBox jCheckBox236;
    private javax.swing.JCheckBox jCheckBox237;
    private javax.swing.JCheckBox jCheckBox238;
    private javax.swing.JCheckBox jCheckBox239;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JCheckBox jCheckBox240;
    private javax.swing.JCheckBox jCheckBox241;
    private javax.swing.JCheckBox jCheckBox242;
    private javax.swing.JCheckBox jCheckBox243;
    private javax.swing.JCheckBox jCheckBox244;
    private javax.swing.JCheckBox jCheckBox245;
    private javax.swing.JCheckBox jCheckBox246;
    private javax.swing.JCheckBox jCheckBox247;
    private javax.swing.JCheckBox jCheckBox248;
    private javax.swing.JCheckBox jCheckBox249;
    private javax.swing.JCheckBox jCheckBox25;
    private javax.swing.JCheckBox jCheckBox250;
    private javax.swing.JCheckBox jCheckBox251;
    private javax.swing.JCheckBox jCheckBox252;
    private javax.swing.JCheckBox jCheckBox253;
    private javax.swing.JCheckBox jCheckBox254;
    private javax.swing.JCheckBox jCheckBox255;
    private javax.swing.JCheckBox jCheckBox256;
    private javax.swing.JCheckBox jCheckBox26;
    private javax.swing.JCheckBox jCheckBox27;
    private javax.swing.JCheckBox jCheckBox28;
    private javax.swing.JCheckBox jCheckBox29;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox30;
    private javax.swing.JCheckBox jCheckBox31;
    private javax.swing.JCheckBox jCheckBox32;
    private javax.swing.JCheckBox jCheckBox33;
    private javax.swing.JCheckBox jCheckBox34;
    private javax.swing.JCheckBox jCheckBox35;
    private javax.swing.JCheckBox jCheckBox36;
    private javax.swing.JCheckBox jCheckBox37;
    private javax.swing.JCheckBox jCheckBox38;
    private javax.swing.JCheckBox jCheckBox39;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox40;
    private javax.swing.JCheckBox jCheckBox41;
    private javax.swing.JCheckBox jCheckBox42;
    private javax.swing.JCheckBox jCheckBox43;
    private javax.swing.JCheckBox jCheckBox44;
    private javax.swing.JCheckBox jCheckBox45;
    private javax.swing.JCheckBox jCheckBox46;
    private javax.swing.JCheckBox jCheckBox47;
    private javax.swing.JCheckBox jCheckBox48;
    private javax.swing.JCheckBox jCheckBox49;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox50;
    private javax.swing.JCheckBox jCheckBox51;
    private javax.swing.JCheckBox jCheckBox52;
    private javax.swing.JCheckBox jCheckBox53;
    private javax.swing.JCheckBox jCheckBox54;
    private javax.swing.JCheckBox jCheckBox55;
    private javax.swing.JCheckBox jCheckBox56;
    private javax.swing.JCheckBox jCheckBox57;
    private javax.swing.JCheckBox jCheckBox58;
    private javax.swing.JCheckBox jCheckBox59;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox60;
    private javax.swing.JCheckBox jCheckBox61;
    private javax.swing.JCheckBox jCheckBox62;
    private javax.swing.JCheckBox jCheckBox63;
    private javax.swing.JCheckBox jCheckBox64;
    private javax.swing.JCheckBox jCheckBox65;
    private javax.swing.JCheckBox jCheckBox66;
    private javax.swing.JCheckBox jCheckBox67;
    private javax.swing.JCheckBox jCheckBox68;
    private javax.swing.JCheckBox jCheckBox69;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox70;
    private javax.swing.JCheckBox jCheckBox71;
    private javax.swing.JCheckBox jCheckBox72;
    private javax.swing.JCheckBox jCheckBox73;
    private javax.swing.JCheckBox jCheckBox74;
    private javax.swing.JCheckBox jCheckBox75;
    private javax.swing.JCheckBox jCheckBox76;
    private javax.swing.JCheckBox jCheckBox77;
    private javax.swing.JCheckBox jCheckBox78;
    private javax.swing.JCheckBox jCheckBox79;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox80;
    private javax.swing.JCheckBox jCheckBox81;
    private javax.swing.JCheckBox jCheckBox82;
    private javax.swing.JCheckBox jCheckBox83;
    private javax.swing.JCheckBox jCheckBox84;
    private javax.swing.JCheckBox jCheckBox85;
    private javax.swing.JCheckBox jCheckBox86;
    private javax.swing.JCheckBox jCheckBox87;
    private javax.swing.JCheckBox jCheckBox88;
    private javax.swing.JCheckBox jCheckBox89;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JCheckBox jCheckBox90;
    private javax.swing.JCheckBox jCheckBox91;
    private javax.swing.JCheckBox jCheckBox92;
    private javax.swing.JCheckBox jCheckBox93;
    private javax.swing.JCheckBox jCheckBox94;
    private javax.swing.JCheckBox jCheckBox95;
    private javax.swing.JCheckBox jCheckBox96;
    private javax.swing.JCheckBox jCheckBox97;
    private javax.swing.JCheckBox jCheckBox98;
    private javax.swing.JCheckBox jCheckBox99;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JList<String> jlIncomingList;
    private javax.swing.JPanel jpChat;
    private javax.swing.JPanel jpControls;
    private javax.swing.JPanel jpHeader;
    private javax.swing.JPanel jpInstructions;
    private javax.swing.JPanel jpInstruments;
    private javax.swing.JTextField tfUserMessage;
    // End of variables declaration//GEN-END:variables
}
