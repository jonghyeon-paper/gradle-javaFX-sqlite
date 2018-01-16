package mvvm.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mvvm.sample.jpa.SampleService;
import mvvm.sample.jpa.Users;

@SpringBootApplication
public class Starter extends Application {
	
	private static ApplicationContext context;

	public static void main(String... args) {
		context = SpringApplication.run(Starter.class, args);
		Application.launch(args);
	}
	
	@Override
	public void init() throws Exception {
		//TODO
		SampleService sampleService = context.getBean(SampleService.class);
		for (Users item : sampleService.getUsersList()) {
			System.out.println(item.toString());
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		//TODO
		stage.setTitle("Hello World Application");

		final ViewTuple<HelloWorldView, HelloWorldViewModel> viewTuple = FluentViewLoader.fxmlView(HelloWorldView.class).load();

		final Parent root = viewTuple.getView();
		stage.setScene(new Scene(root, 800, 600));
		stage.show();
	}

}