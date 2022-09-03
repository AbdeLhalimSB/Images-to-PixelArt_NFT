
package images.to.pixelart_nft;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author sahba
 */
public class ImagesToPixelArt_NFT extends Application {
    
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Images to PixelArt NFT");
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
