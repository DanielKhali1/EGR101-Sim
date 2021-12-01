
package com.egr101sim.ui;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.GenericStyledArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.Paragraph;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.collection.ListModification;
import com.egr101sim.core.ApplicationManager;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.ContextMenu; 
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class MainUI extends Application {
	
	Pane pane;
	Scene scene;
	ApplicationManager manager;
	Thread thread;
	Process runProcess;
	
	private static final String[] KEYWORDSblue = new String[] {
		"HIGH", "LOW", "INPUT", "INPUT_PULLUP", "OUTPUT", "DEC", "BIN",
		"HEX", "OCT", "PI", "HALF_PI", "TWO_PI", "LSBFIRST", "MSBFIRST", 
		"CHANGE", "FALLING", "RISING", "DEFAULT", "EXTERNAL", "INTERNAL",
		"INTERNAL1V1", "INTERNAL2V56", "LED_BUILTIN", "LED_BUILTIN_RX", 
		"LED_BUILTIN_TX", "DIGITAL_MESSAGE", "FIRMATA_STRING", "ANALOG_MESSAGE",
		"REPORT_DIGITAL", "REPORT_ANALOG", "SET_PIN_MODE", "SYSTEM_RESET", "SYSEX_START",
		"auto", "int8_t", "int16_t", "int32_t", "int64_t", "uint8_t", "uint16_t", 
		"uint32_t", "uint64_t", "char16_t", "char32_t", "operator", "enum", "delete",
		"boolean", "bool","byte", "char", "const", "false", "float", "double", "null", 
		"NULL", "int", "long", "new", "private", "protected", "public", "short", "signed",
		"static", "volatile", "String", "void", "true", "unsigned", "word", "array",
		"sizeof", "dynamic_cast", "typedef", "const_cast", "struct", "static_cast", 
		"union", "friend", "extern", "class", "reinterpret_cast", "register", "explicit",
		"inline", "_Bool", "complex", "_Complex", "_Imaginary", "atomic_bool", "atomic_char", 
		"atomic_schar", "atomic_uchar", "atomic_short", "atomic_ushort", "atomic_int", "atomic_uint",
		"atomic_long", "atomic_ulong", "atomic_llong", "atomic_ullong", "virtual",
		"PROGMEM"
	};
	
	private static final String[] KEYWORDSorange = new String[] {
		"abs", "acos", "acosf", "asin", "asinf", "atan", "atan2", "atan2f", "atanf", "cbrt",
		"cbrtf", "ceil", "ceilf", "constrain", "copysign", "copysignf", "cos", "cosf",
		"cosh", "coshf", "degrees", "exp", "expf", "fabs", "fabsf", "fdim", "fdimf", "floor", 
		"floorf", "fma", "fmaf", "fmax", "fmaxf", "fmin", "fminf", "fmod", "fmodf", 
		"hypot", "hypotf", "isfinite", "isinf", "isnan", "ldexp", "ldexpf", "log", "log10", "log10f",
		"logf", "lrint", "lrintf", "lround", "lroundf", "map", "max", "min", "pow", "powf", "radians",
		"random", "randomSeed", "round", "roundf", "signbit", "sin", "sinh", "sinhf", "sq", "sqrt", "sqrtf",
		"sqrtf", "tan", "tanf", "tanh", "tanhf", "trunc", "truncf", "bitRead", "bitWrite", "bitSet", "bitClear",
		"bit", "highByte", "lowByte", "analogReference", "analogReadResolution", "analogRead", 
		"analogWriteResolution", "analogWrite", "attachInterrupt", "detachInterrupt", "digitalPinToInterrupt", "delay",
		"delayMicroseconds", "digitalWrite", "digitalRead", "interrupts", "millis", "micros", "noInterrupts",
		"noTone", "pinMode", "pulseIn", "pulseInLong", "shiftIn", "shiftOut", "tone", "yield", "Stream", 
		"Serial", "Serial1", "Serial2", "Serial3", "SerialUSB", "begin", "end", "peek", "read", "println", "print",
		"avaliable", "avaliableForWrite", "flush", "setTimeout", "findUntil", "find", "parseInt", "parseFloat", 
		"readBytes", "readBytesUntil", "readString", "readStringUntil", "trim", "toUpperCase",
		"toLowerCase", "charAt", "compareTo", "concat", "endsWith", "startsWith", "equals", "equalsIgnoreCase", 
		"getBytes", "indexOf", "lastIndexOf", "length", "replace", "setCharAt", "substring", "toCharArray", "toInt",
		"Keyboard", "Mouse", "press", "release", "releaseAll", "accept", "click", "move", "isPressed", 
		"isAlphaNumeric", "isAlpha", "isAscii", "isWhitespace", "isControl", "isDigit", "isGraph", "isLowerCase", 
		"isPrintable", "isPunct", "isSpace", "isUpperCase", "isHexadecimalDigit"
	};
	
	private static final String[] KEYWORDSgreen = new String[] {
		"break", "case", "override", "final", "continue", "default", "do", "#else", "#error", "#if", "else", "for", "if", "return", "goto",
		"switch", "throw", "try", "while", "setup", "loop", "export", "not", "or", "and", "xor", "#include", "#define", 
		"#elif",  "#ifdef", "#ifndef", "#pragma", "#warning"
	};
	
	private static final String KEYWORDblue_PATTERN = String.join("|", KEYWORDSblue);
	
	private static final String KEYWORDgreen_PATTERN = String.join("|", KEYWORDSgreen);
	
	private static final String KEYWORDorange_PATTERN = String.join("|", KEYWORDSorange);
	
	private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/"   // for whole text processing (text blocks)
             + "|" + "/\\*[^\\v]*" + "|" + "^\\h*\\*([^\\v]*|/)";  // for visible paragraph processing (line by line)
	
	private static final Pattern PATTERN = Pattern.compile(
		"(?<KEYWORDblue>" + KEYWORDblue_PATTERN + ")"
		+ "|(?<KEYWORDgreen>" + KEYWORDgreen_PATTERN + ")"
		+ "|(?<KEYWORDorange>" + KEYWORDorange_PATTERN + ")"
		+ "|(?<COMMENT>" + COMMENT_PATTERN + ")"
	);
	
	private static final String startingcode = String.join("\n", new String[] 
			{
					"void setup() {\r\n" + 
							  "  // put your set up code here, to run once:\r\n" + 
							  "\r\n" + 
							  "}\r\n" + 
							  "\r\n" + 
							  "void loop() {\r\n" + 
							  "  // put your main code here, to run repeatedly:\r\n" + 
							  "\r\n" + 
							  "}"
			});
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		manager = new ApplicationManager();
		pane = new Pane();
		
		CodeArea codeArea = new CodeArea();
		
		codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea)); 
		codeArea.setContextMenu(new DefaultContextMenu());
		
		codeArea.getVisibleParagraphs().addModificationObserver
		(
			new VisibleParagraphStyler<>(codeArea, this::computeHighlighting)
		);
		
		final Pattern whiteSpace = Pattern.compile("^\\s+");
		codeArea.addEventHandler( KeyEvent.KEY_PRESSED, KE -> 
		{
			if(KE.getCode() == KeyCode.ENTER)
			{
				int caretPosition = codeArea.getCaretPosition();
				int currentParagraph = codeArea.getCurrentParagraph();
				Matcher m0 = whiteSpace.matcher(codeArea.getParagraph(currentParagraph - 1).getSegments().get(0));
				if(m0.find())
				{
					Platform.runLater(() -> codeArea.insertText(caretPosition, m0.group()));
				}
			}
		});
		
		codeArea.replaceText(0, 0, startingcode);
		
		codeArea.relocate(0, 100);
		codeArea.setPrefSize(1000, 400);
		
		// colors for background 
		
		Color orangeText = Color.web("#d35400");
		Color greenText = Color.web("#728E00");
		Color blueText = Color.web("#00979c");
		Color lightGreen = Color.web("#17a1a5");
		Color darkGreen = Color.web("#006468");
		Color textGreen = Color.web("#0f6464");
		
		Rectangle rectangle = new Rectangle();
		rectangle.setFill(lightGreen);
		rectangle.setX(0);
		rectangle.setY(60);
		rectangle.setWidth(1000);
		rectangle.setHeight(40); 
		
		Rectangle rectangle2 = new Rectangle();
		rectangle2.setFill(darkGreen);
		rectangle2.setX(0);
		rectangle2.setY(30);
		rectangle2.setWidth(1000);
		rectangle2.setHeight(40); 
		
		Rectangle rectangle4 = new Rectangle();
		rectangle4.setFill(lightGreen);
		rectangle4.setX(0);
		rectangle4.setY(500);
		rectangle4.setWidth(1000);
		rectangle4.setHeight(30); 
		
		Rectangle rectangle5 = new Rectangle();
		rectangle5.setFill(Color.BLACK);
		rectangle5.setX(0);
		rectangle5.setY(520);
		rectangle5.setWidth(1000);
		rectangle5.setHeight(170); 
		
		Rectangle rectangle6 = new Rectangle();
		rectangle6.setFill(darkGreen);
		rectangle6.setX(0);
		rectangle6.setY(670);
		rectangle6.setWidth(1000);
		rectangle6.setHeight(50); 
		
		Rectangle cover = new Rectangle();
		cover.setFill(Color.BLACK);
		cover.setX(0);
		cover.setY(520);
		cover.setWidth(1000);
		cover.setHeight(170); 
		
		Rectangle rectangle3 = new Rectangle();
		rectangle3.setFill(Color.WHITE);
		rectangle3.setX(20);
		rectangle3.setY(70);
		rectangle3.setWidth(80);
		rectangle3.setHeight(60); 
		rectangle3.setArcHeight(10);
		rectangle3.setArcWidth(10);
		
		Text t = new Text();
		t.setX(30);
		t.setY(90);
		t.setText("sketch_");
		t.setFill(textGreen);
	
		Text console = new Text();
		console.setX(10);
		console.setY(535);
		console.setText("Console Output");
		console.setFill(Color.WHITE);
		
		Button run = new Button("Run");
		run.relocate(0, 35);
		run.setPrefSize(40, 30);
		
		Button build = new Button("Build");
		build.relocate(45, 35);
		build.setPrefSize(45, 30);
		
		Button newFile = new Button("New File");
		newFile.relocate(95, 35);
		newFile.setPrefSize(65, 30);

		Button open = new Button("Open");
		open.relocate(165, 35);
		open.setPrefSize(50, 30);
		
		Button save = new Button("Save");
		save.relocate(220,35);
		save.setPrefSize(50, 30);
		
		Button wiring = new Button("Bot Design");
		wiring.relocate(880, 35);
		wiring.setPrefSize(110, 30);
		
		pane.getChildren().addAll(rectangle4, rectangle, rectangle3, rectangle6, codeArea, rectangle2, t,run, build, 
				newFile, rectangle5, open, save, wiring, console, ToolBar(primaryStage));
		
		scene = new Scene(pane, 1000, 760);
		
		wiring.setOnAction(e -> 
		{
			try {Process process = Runtime.getRuntime().exec("..\\..\\Executables\\customization\\Unity_Project.exe");} 
			catch (IOException e1){e1.printStackTrace();}
		});

		build.setOnAction(e->{
			if (!manager.isSimRunning()) {
				manager.updateBehavior(codeArea.getText());
			}
			//console output 
			console.setText(manager.stackPrint());
		});
		
		run.setOnAction(e->{
			
			if(run.getText().equals("Run"))
			{
				try {runProcess = Runtime.getRuntime().exec("..\\..\\Executables\\simulation\\Unity_Project.exe");} 
				catch (IOException e1){e1.printStackTrace();}
				
				run.setText("End");
					Platform.runLater(new Runnable() {
					      @Override
					      public void run() {
					    	  
					    	  thread = (new Thread(() -> {
					    		  manager.setSimRunning(true);
					    		  manager.execute();
					    		  System.out.println("thread over");
					    	  }));
					    	  thread.start();
					      }
					  });
			}
			else {
				run.setText("Run");
				runProcess.destroy();
				manager.setSimRunning(false);
			}
		});

		File f = new File("Styles.css");
		scene.getStylesheets().add("File:///"+f.getAbsolutePath().replace("\\","/"));
		
		//Scene scene = new Scene(new StackPane(new VirtualizedScrollPane<>(codeArea)), 1000, 760);
		primaryStage.setScene(scene);
		primaryStage.setTitle("EGR101 Simulation Software");
		primaryStage.show();
		
	}
	
	public ToolBar ToolBar(Stage stage)
	{
		FileChooser fileChooser = new FileChooser();
		ToolBar toolBar = new ToolBar();
		
		MenuItem btnfile = new MenuItem("New");
		
		MenuItem btnsketch = new MenuItem("Open");
		
		btnsketch.setOnAction(e ->{
			fileChooser.setTitle("Open Resource File");
			fileChooser.showOpenDialog(stage);
		});
		
		MenuItem btnSave = new MenuItem("Save");
		
		MenuItem btnSaveAs = new MenuItem("Save As");
		
		MenuItem btnSaveConfig = new MenuItem("Save Config File");
		
		MenuButton file = new MenuButton("File");
		file.setPrefSize(70,20);
		
		file.getItems().addAll(btnfile, btnsketch , btnSave, btnSaveAs, btnSaveConfig);
		
		MenuItem btnVerifyCompile = new MenuItem("Verify/Compile");
		
		MenuItem btnUpload = new MenuItem("Upload");
		
		MenuButton sketch = new MenuButton("Sketch");
		sketch.setPrefSize(70,20);
		
		sketch.getItems().addAll(btnVerifyCompile, btnUpload);
		
		MenuItem btnSerialMon = new MenuItem("Serial Monitor");
		
		MenuButton tools = new MenuButton("Tools");
		tools.setPrefSize(70, 20);
		
		tools.getItems().addAll(btnSerialMon);
		
		toolBar.getItems().addAll(file, new Separator(), sketch, new Separator(), tools);
		
		return toolBar;
	}
	
	private StyleSpans<Collection<String>> computeHighlighting(String text)
	{
		Matcher matcher = PATTERN.matcher(text);
		int lastKeyWordEnd = 0; 
		StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
		while(matcher.find()) 
		{
		    String styleClass =
		            matcher.group("KEYWORDblue") != null ? "keywordblue" :
		            matcher.group("KEYWORDgreen") != null ? "keywordgreen" :
		            matcher.group("KEYWORDorange") != null ? "keywordorange" :
		            matcher.group("COMMENT") != null ? "comment" :
		            null; 
		    		assert styleClass != null;
		    spansBuilder.add(Collections.emptyList(), matcher.start() - lastKeyWordEnd);
		    spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
		    lastKeyWordEnd = matcher.end();
		}
		spansBuilder.add(Collections.emptyList(), text.length() - lastKeyWordEnd);
		return spansBuilder.create(); 
	}
	
	private class VisibleParagraphStyler<PS, SEG, S> implements Consumer<ListModification<? extends Paragraph<PS, SEG, S>>>
	{
		private final GenericStyledArea<PS, SEG, S> area;
	    private final Function<String,StyleSpans<S>> computeStyles;
	    private int prevParagraph, prevTextLength;
	    
	    public VisibleParagraphStyler( GenericStyledArea<PS, SEG, S> area, Function<String,StyleSpans<S>> computeStyles )
        {
            this.computeStyles = computeStyles;
            this.area = area;
        } 
	    
	    @Override 
	    public void accept(ListModification<? extends Paragraph<PS, SEG, S>> lm)
	    {
	    	if (lm.getAddedSize() > 0)
	    	{
	    		int paragraph = Math.min(area.firstVisibleParToAllParIndex() + lm.getFrom(), area.getParagraphs().size() - 1);
	    		String text = area.getText(paragraph, 0, paragraph, area.getParagraphLength(paragraph));
	    		
	    		if(paragraph != prevParagraph || text.length() != prevTextLength)
	    		{
	    			int startPos = area.getAbsolutePosition(paragraph, 0);
	    			Platform.runLater(() -> area.setStyleSpans(startPos, computeStyles.apply(text)));
	    			prevTextLength = text.length();
	    			prevParagraph = paragraph; 
	    		}
	    	}
	    }
	}
	
	private class DefaultContextMenu extends ContextMenu
	{
		private MenuItem fold, unfold, print; 
		
		public DefaultContextMenu()
		{
			fold = new MenuItem("Fold selected text");
			fold.setOnAction(AE -> { hide(); fold(); } );
			
			unfold = new MenuItem( "Unfold from cursor" );
            unfold.setOnAction( AE -> { hide(); unfold(); } );

            print = new MenuItem( "Print" );
            print.setOnAction( AE -> { hide(); print(); } );

            getItems().addAll( fold, unfold, print );
		}
		
		private void fold() 
		{
            ((CodeArea) getOwnerNode()).foldSelectedParagraphs();
        }

        private void unfold() 
        {
            CodeArea area = (CodeArea) getOwnerNode();
            area.unfoldParagraphs( area.getCurrentParagraph() );
        }

        private void print() 
        {
            System.out.println( ((CodeArea) getOwnerNode()).getText() );
        }
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
