# BeatBox

This is a modified and updated version of the Beat Box program found in the Head First Java book.

## Features

The program has all the capabalities as the originial program, and also some more.

### Original features:

- Pick a variation of the predefined musical instruments and play it in a continuous loop.
- Speed it up, or slow it down, do whatever you like.
- Send it to your friends, and listen to the tunes they have sent you.

### Extra features:

- It is possible to cancel what you wrote in the chat window before sending.
- <b>Exit</b> button added for graceful shutdown.
- Cleaner and nicer looking GUI.
- A <b>How to Use</b> field is added to the GUI with basic instructions.
- <b>Clear Checkboxes</b> button added.
- Deprecated variable types (such as Vectors) are removed.
- The code is refactored to use Java 8 capabilities, like lambda expressions.
- A lot of extra comments are added for better understanding.
- If the server is not available, the <b>Send Rythm & Message</b> button is disabled.

## GUI Overview

<a href="https://imgur.com/LGG3A9b"><img src="https://i.imgur.com/LGG3A9b.jpg" title="beatbox client main" /></a>

1. Over the top title: sometimes one superlative is just not enough. (It would have been in this case though.)
2. Instruments: the main part of the program.
3. How to use: basic instructions in a scrollable view.
4. Controls: buttons for controlling the application.
5. Chat: shows incoming and outgoing messages.

## How to Use

In order to use all the capabilites of the Beat box application, a running server is needed. The code for the server is found in the <i>BeatBox/src/main/java/beatboxServer/</i> folder.
After the server is running, start the application.

<b>Note</b>: If the server is not available, the <b>Send Rythm & Message</b> button is disabled. However, you can still use all the other functions.

- To crate a music pattern, click the checkboxes in the Instruments panel. The app supports 16 notes for 16 beats. 
- To start the music, click the <b>Start</b> button. The program automatically loops, and plays the sequence.
- To stop the playback, click <b>Stop</b>.
- To increase the tempo of the sequence by 3%, click <b>Tempo Up</b>. Click repeatedly to increase more.
- To decrease the tempo of the sequence to 97%, click <b>Tempo Down</b>. Click repeatedly to decrease more.
- To remove all the checkboxes, click <b>Clear Checkboxes</b>.
- To quit the application, click <b>Exit</b>.
- To send your message and music pattern to the chat, click <b>Send Rythm & Message</b>.
- To clear your message, click <b>Clear Message</b>.
