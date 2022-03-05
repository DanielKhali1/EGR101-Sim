package com.egr101sim.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.BindException;
import java.security.Timestamp;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.GenericStyledArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.Paragraph;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.collection.ListModification;
import org.reactfx.Subscription;

import com.egr101sim.core.ApplicationManager;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.ContextMenu;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.*;
import java.util.Observable;
import java.util.Observer;

public class MainUI extends Application {

	Pane pane;
	Scene scene;
	ApplicationManager manager;
	Thread thread;
	Process process;

	private static final String[] KEYWORDSblue = new String[] { "HIGH", "LOW", "INPUT_PULLUP", "INPUT", "OUTPUT", "DEC",
			"BIN", "HEX", "OCT", "PI", "HALF_PI", "TWO_PI", "LSBFIRST", "MSBFIRST", "CHANGE", "FALLING", "RISING",
			"DEFAULT", "EXTERNAL", "INTERNAL", "INTERNAL1V1", "INTERNAL2V56", "LED_BUILTIN", "LED_BUILTIN_RX",
			"LED_BUILTIN_TX", "DIGITAL_MESSAGE", "FIRMATA_STRING", "ANALOG_MESSAGE", "REPORT_DIGITAL", "REPORT_ANALOG",
			"SET_PIN_MODE", "SYSTEM_RESET", "SYSEX_START", "auto", "int8_t", "int16_t", "int32_t", "int64_t", "uint8_t",
			"uint16_t", "uint32_t", "uint64_t", "char16_t", "char32_t", "operator", "enum", "delete", "boolean", "bool",
			"byte", "char", "const", "false", "float", "double", "null", "NULL", "int", "long", "new", "private",
			"protected", "public", "short", "signed", "static", "volatile", "String", "void", "true", "unsigned",
			"word", "array", "sizeof", "dynamic_cast", "typedef", "const_cast", "struct", "static_cast", "union",
			"friend", "extern", "class", "reinterpret_cast", "register", "explicit", "inline", "_Bool", "complex",
			"_Complex", "_Imaginary", "atomic_bool", "atomic_char", "atomic_schar", "atomic_uchar", "atomic_short",
			"atomic_ushort", "atomic_int", "atomic_uint", "atomic_long", "atomic_ulong", "atomic_llong",
			"atomic_ullong", "virtual", "PROGMEM" };

	private static final String[] KEYWORDSorange = new String[] { "abs", "acos", "acosf", "asin", "asinf", "atan",
			"atan2", "atan2f", "atanf", "cbrt", "cbrtf", "ceil", "ceilf", "constrain", "copysign", "copysignf", "cos",
			"cosf", "coshf", "cosh", "degrees", "expf", "exp", "fabsf", "fabs", "fdimf", "fdim", "floorf", "floor",
			"fmaf", "fmaf", "fmaxf", "fmax", "fminf", "fmin", "fmodf", "fmod", "hypotf", "hypot", "isfinite", "isinf",
			"isnan", "ldexpf", "ldexp", "log10f", "log10", "log", "logf", "lrint", "lrintf", "lround", "lroundf", "map",
			"max", "min", "pow", "powf", "radians", "random", "randomSeed", "round", "roundf", "signbit", "sinhf",
			"sinh", "sin", "sqrtf", "sqrt", "sq", "tanhf", "tanf", "tanh", "tan", "trunc", "truncf", "bitRead",
			"bitWrite", "bitSet", "bitClear", "bit", "highByte", "lowByte", "analogReference", "analogReadResolution",
			"analogRead", "analogWriteResolution", "analogWrite", "attachInterrupt", "detachInterrupt",
			"digitalPinToInterrupt", "delay", "delayMicroseconds", "digitalWrite", "digitalRead", "interrupts",
			"millis", "micros", "noInterrupts", "noTone", "pinMode", "pulseIn", "pulseInLong", "shiftIn", "shiftOut",
			"tone", "yield", "Stream", "Serial", "Serial1", "Serial2", "Serial3", "SerialUSB", "begin", "end", "peek",
			"read", "println", "print", "avaliable", "avaliableForWrite", "flush", "setTimeout", "findUntil", "find",
			"parseInt", "parseFloat", "readBytes", "readBytesUntil", "readString", "readStringUntil", "trim",
			"toUpperCase", "toLowerCase", "charAt", "compareTo", "concat", "endsWith", "startsWith", "equals",
			"equalsIgnoreCase", "getBytes", "indexOf", "lastIndexOf", "length", "replace", "setCharAt", "substring",
			"toCharArray", "toInt", "Keyboard", "Mouse", "press", "release", "releaseAll", "accept", "click", "move",
			"isPressed", "isAlphaNumeric", "isAlpha", "isAscii", "isWhitespace", "isControl", "isDigit", "isGraph",
			"isLowerCase", "isPrintable", "isPunct", "isSpace", "isUpperCase", "isHexadecimalDigit" };

	private static final String[] KEYWORDSgreen = new String[] { "break", "case", "override", "final", "continue",
			"default", "do", "#else", "#error", "#if", "else", "for", "if", "return", "goto", "switch", "throw", "try",
			"while", "setup", "loop", "export", "not", "or", "and", "xor", "#include", "#define", "#elif", "#ifdef",
			"#ifndef", "#pragma", "#warning" };

	private static final String KEYWORDblue_PATTERN = String.join("|", KEYWORDSblue);

	private static final String KEYWORDgreen_PATTERN = String.join("|", KEYWORDSgreen);

	private static final String KEYWORDorange_PATTERN = String.join("|", KEYWORDSorange);

	private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/" // for whole text processing
																							// (text blocks)
			+ "|" + "/\\*[^\\v]*" + "|" + "^\\h*\\*([^\\v]*|/)"; // for visible paragraph processing (line by line)

	private static final Pattern PATTERN = Pattern
			.compile("(?<KEYWORDblue>" + KEYWORDblue_PATTERN + ")" + "|(?<KEYWORDgreen>" + KEYWORDgreen_PATTERN + ")"
					+ "|(?<KEYWORDorange>" + KEYWORDorange_PATTERN + ")" + "|(?<COMMENT>" + COMMENT_PATTERN + ")");

	private static final String startingcode = String.join("\n",
			new String[] { "void setup() {\r\n" + "  // put your set up code here, to run once:\r\n" + "\r\n" + "}\r\n"
					+ "\r\n" + "void loop() {\r\n" + "  // put your main code here, to run repeatedly:\r\n" + "\r\n"
					+ "}" });

	@Override
	public void start(Stage primaryStage) throws Exception {
		manager = new ApplicationManager();
		pane = new Pane();

		CodeArea codeArea = new CodeArea();

		codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
		codeArea.setContextMenu(new DefaultContextMenu());

		codeArea.getVisibleParagraphs()
				.addModificationObserver(new VisibleParagraphStyler<>(codeArea, this::computeHighlighting));

		final Pattern whiteSpace = Pattern.compile("^\\s+");
		codeArea.addEventHandler(KeyEvent.KEY_PRESSED, KE -> {
			if (KE.getCode() == KeyCode.ENTER) {
				int caretPosition = codeArea.getCaretPosition();
				int currentParagraph = codeArea.getCurrentParagraph();
				Matcher m0 = whiteSpace.matcher(codeArea.getParagraph(currentParagraph - 1).getSegments().get(0));
				if (m0.find()) {
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
		rectangle3.setX(30);
		rectangle3.setY(70);
		rectangle3.setWidth(160);
		rectangle3.setHeight(60);
		rectangle3.setArcHeight(10);
		rectangle3.setArcWidth(10);

		Rectangle rectanglefix = new Rectangle();
		rectangle3.setFill(Color.WHITE);
		rectangle3.setX(30);
		rectangle3.setY(70);
		rectangle3.setWidth(160);
		rectangle3.setHeight(60);
		rectangle3.setArcHeight(10);
		rectangle3.setArcWidth(10);

		Date date = new Date();
		String tempName = "sketch_" + date.getTime() + ".ino";

		Text t = new Text();
		t.setX(40);
		t.setY(90);
		t.setText(tempName);
		t.setFill(textGreen);

		Text console = new Text();
		console.setX(10);
		console.setY(535);
		console.setText("");
		console.setFill(Color.WHITE);

		Image runImg = new Image("file:Resources\\arro.JPG");
		ImageView runimage = new ImageView();
		runimage.setImage(runImg);
		runimage.setX(40);
		runimage.setY(27);
		runimage.setFitHeight(43);
		runimage.setFitWidth(40);

		Image buildImg = new Image("file:Resources\\Checkmark.JPG");
		ImageView buildimage = new ImageView();
		buildimage.setImage(buildImg);
		buildimage.setX(0);
		buildimage.setY(30);
		buildimage.setFitHeight(40);
		buildimage.setFitWidth(40);

		Image newFileImage = new Image("file:Resources\\newfile.JPG");
		ImageView newimage = new ImageView();
		newimage.setImage(newFileImage);
		newimage.setX(80);
		newimage.setY(30);
		newimage.setFitHeight(40);
		newimage.setFitWidth(40);

		Image openImage = new Image("file:Resources\\upload.JPG");
		ImageView openimage = new ImageView();
		openimage.setImage(openImage);
		openimage.setX(120);
		openimage.setY(33);
		openimage.setFitHeight(35);
		openimage.setFitWidth(40);

		Image saveImage = new Image("file:Resources\\download.JPG");
		ImageView saveimage = new ImageView();
		saveimage.setImage(saveImage);
		saveimage.setX(160);
		saveimage.setY(31);
		saveimage.setFitHeight(37);
		saveimage.setFitWidth(40);

		Button wiring = new Button("Wiring Interface");
		wiring.relocate(880, 35);
		wiring.setPrefSize(110, 30);

		Button botCustomization = new Button("Bot Customization");
		botCustomization.relocate(750, 35);
		botCustomization.setPrefSize(120, 30);

		pane.getChildren().addAll(rectangle4, rectangle, rectangle3, rectangle6, codeArea, rectangle2, t, rectangle5,
				wiring, botCustomization, console);

		pane.getChildren().addAll(runimage, buildimage, newimage, saveimage, openimage);

		pane.getChildren().addAll(ToolBar(primaryStage));

		scene = new Scene(pane, 1000, 760);

		wiring.setOnAction(e -> {
			try {
				Runtime runTime = Runtime.getRuntime();
				String executablePath = "..\\..\\Executables\\wiring\\Unity_Project.exe";
				Process process = runTime.exec(executablePath);
				// new WiringGUI(manager).start(new Stage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		botCustomization.setOnAction(e -> {
			try {
				Runtime runTime = Runtime.getRuntime();
				String executablePath = "..\\..\\Executables\\customization\\Unity_Project.exe";
				Process process = runTime.exec(executablePath);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		buildimage.setOnMouseClicked(e -> {
			if (!manager.isSimRunning()) {
				manager.updateBehavior(codeArea.getText(), console);
			}
			// console output
			console.setText(console.getText() + "\n" + manager.stackPrint());
		});

		runimage.setOnMouseClicked(e -> {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {

					if (manager.simManager.getArduino().behavior.getFunction() != null) {
						try {
							Runtime runTime = Runtime.getRuntime();
							String executablePath = "..\\..\\Executables\\simulation\\Unity_Project.exe";
							process = runTime.exec(executablePath);

						} catch (Exception e1) {
							e1.printStackTrace();
						}

						thread = (new Thread(() -> {
							manager.setSimRunning(true);
							try {
								manager.execute(process, console);

							} catch (NullPointerException e3) {
								process.destroy();
								manager.setSimRunning(false);
								manager.simManager.shutDown(console);
							}

							System.out.println("arduino execution thread over");
						}));

						thread.start();
					} else {
						console.setText("Verify before running!");
					}
				}
			});
			manager.setSimRunning(false);
		});

		saveimage.setOnMouseClicked(e -> {
			try {
				FileChooser fileChooser1 = new FileChooser();
				fileChooser1.setInitialFileName(tempName);
				File file = fileChooser1.showSaveDialog(primaryStage);
				FileWriter fw = new FileWriter(file);
				String code = codeArea.textProperty().getValue();
				fw.write(code);
				fw.close();

			} catch (Exception e2) {
				console.setText("ERROR: Unable to save file");
			}
		});

		openimage.setOnMouseClicked(e -> {
			try {
				FileChooser fileChooser2 = new FileChooser();
				File Opened = fileChooser2.showOpenDialog(primaryStage);

				BufferedReader in = new BufferedReader(new FileReader(Opened));
				String str = "";
				String code = "";

				while ((str = in.readLine()) != null) {
					code = code + str + "\n";
				}

				codeArea.clear();
				codeArea.replaceText(0, 0, code);
				codeArea.relocate(0, 100);
				codeArea.setPrefSize(1000, 400);
			} catch (Exception e2) {
				console.setText("ERROR: Unable to open file");
			}
		});

		newimage.setOnMouseClicked(e -> {
			try {
				Date date2 = new Date();
				String tempName2 = "sketch_" + date2.getTime() + ".ino";

				codeArea.clear();
				codeArea.replaceText(0, 0, startingcode);

				Text t2 = new Text();
				t.setX(40);
				t.setY(90);
				t.setText(tempName2);
				t.setFill(textGreen);

				pane.getChildren().addAll(rectanglefix, t2);
				Stage stage2 = new Stage();
				stage2.setScene(scene);
				stage2.setTitle("EGR101 Simulation Software");
				stage2.show();
			} catch (Exception e2) {
				console.setText("ERROR: Unable to create new file");
				e2.printStackTrace();
			}
		});

		File f = new File("Styles.css");
		scene.getStylesheets().add("File:///" + f.getAbsolutePath().replace("\\", "/"));

		primaryStage.setScene(scene);
		primaryStage.setTitle("EGR101 Simulation Software");
		primaryStage.show();

	}

	public ToolBar ToolBar(Stage stage) {
		FileChooser fileChooser = new FileChooser();
		ToolBar toolBar = new ToolBar();

		MenuItem btnfile = new MenuItem("New");

		MenuItem btnsketch = new MenuItem("Open");

		btnsketch.setOnAction(e -> {
			fileChooser.setTitle("Open Resource File");
			fileChooser.showOpenDialog(stage);
		});

		MenuItem btnSave = new MenuItem("Save");

		MenuItem btnSaveAs = new MenuItem("Save As");

		MenuItem btnSaveConfig = new MenuItem("Save Config File");

		MenuButton file = new MenuButton("File");
		file.setPrefSize(70, 20);

		file.getItems().addAll(btnfile, btnsketch, btnSave, btnSaveAs, btnSaveConfig);

		MenuItem btnVerifyCompile = new MenuItem("Verify/Compile");

		MenuItem btnUpload = new MenuItem("Upload");

		MenuButton sketch = new MenuButton("Sketch");
		sketch.setPrefSize(70, 20);

		sketch.getItems().addAll(btnVerifyCompile, btnUpload);

		MenuItem btnSerialMon = new MenuItem("Serial Monitor");

		MenuButton tools = new MenuButton("Tools");
		tools.setPrefSize(70, 20);

		tools.getItems().addAll(btnSerialMon);

		toolBar.getItems().addAll(file, new Separator(), sketch, new Separator(), tools);

		return toolBar;
	}

	private StyleSpans<Collection<String>> computeHighlighting(String text) {
		Matcher matcher = PATTERN.matcher(text);
		int lastKeyWordEnd = 0;
		StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
		while (matcher.find()) {
			String styleClass = matcher.group("KEYWORDblue") != null ? "keywordblue"
					: matcher.group("KEYWORDgreen") != null ? "keywordgreen"
							: matcher.group("KEYWORDorange") != null ? "keywordorange"
									: matcher.group("COMMENT") != null ? "comment" : null;
			assert styleClass != null;
			spansBuilder.add(Collections.emptyList(), matcher.start() - lastKeyWordEnd);
			spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
			lastKeyWordEnd = matcher.end();
		}
		spansBuilder.add(Collections.emptyList(), text.length() - lastKeyWordEnd);
		return spansBuilder.create();
	}

	private class VisibleParagraphStyler<PS, SEG, S>
			implements Consumer<ListModification<? extends Paragraph<PS, SEG, S>>> {
		private final GenericStyledArea<PS, SEG, S> area;
		private final Function<String, StyleSpans<S>> computeStyles;
		private int prevParagraph, prevTextLength;

		public VisibleParagraphStyler(GenericStyledArea<PS, SEG, S> area,
				Function<String, StyleSpans<S>> computeStyles) {
			this.computeStyles = computeStyles;
			this.area = area;
		}

		@Override
		public void accept(ListModification<? extends Paragraph<PS, SEG, S>> lm) {
			if (lm.getAddedSize() > 0) {
				int paragraph = Math.min(area.firstVisibleParToAllParIndex() + lm.getFrom(),
						area.getParagraphs().size() - 1);
				String text = area.getText(paragraph, 0, paragraph, area.getParagraphLength(paragraph));

				if (paragraph != prevParagraph || text.length() != prevTextLength) {
					int startPos = area.getAbsolutePosition(paragraph, 0);
					Platform.runLater(() -> area.setStyleSpans(startPos, computeStyles.apply(text)));
					prevTextLength = text.length();
					prevParagraph = paragraph;
				}
			}
		}
	}

	private class DefaultContextMenu extends ContextMenu {
		private MenuItem fold, unfold, print;

		public DefaultContextMenu() {
			fold = new MenuItem("Fold selected text");
			fold.setOnAction(AE -> {
				hide();
				fold();
			});

			unfold = new MenuItem("Unfold from cursor");
			unfold.setOnAction(AE -> {
				hide();
				unfold();
			});

			print = new MenuItem("Print");
			print.setOnAction(AE -> {
				hide();
				print();
			});

			getItems().addAll(fold, unfold, print);
		}

		private void fold() {
			((CodeArea) getOwnerNode()).foldSelectedParagraphs();
		}

		private void unfold() {
			CodeArea area = (CodeArea) getOwnerNode();
			area.unfoldParagraphs(area.getCurrentParagraph());
		}

		private void print() {
			System.out.println(((CodeArea) getOwnerNode()).getText());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
