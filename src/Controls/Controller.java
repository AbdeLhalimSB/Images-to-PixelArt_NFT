
package Controls;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author sahba
 */
public class Controller {
    
    private Image currentImage;
    @FXML
    ComboBox levels;
    @FXML
    private ImageView source;
    @FXML
    private ImageView result;
    @FXML
    Label imgc,imgl;
    
    @FXML
    public void initialize() throws SQLException {
        getalllevels();
    }

    private void getalllevels(){
        levels.getItems().addAll("8","16","24","32","40","48","56","64");
        levels.getSelectionModel().select("8");
    }
    
    public void selectsourcefolder(){
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choose Images Folder");
        File selectedDirectory = chooser.showDialog(new Stage());
        File f = new File(selectedDirectory.toString());
        pathnames = f.listFiles();
        imgc.setText("Waiting Start");
        imgl.setText(Integer.toString(pathnames.length));
    }
    File[] pathnames;
    
    public void btnstart(Event e) throws IOException
    {
        DirectoryChooser saver = new DirectoryChooser();
        saver.setTitle("Choose Output Folder");
        File resultfolder = saver.showDialog(new Stage());
        File resulf = resultfolder;
        for(int i=0;i<pathnames.length;i++){
            currentImage = new Image(pathnames[i].toURI().toString());
            source.setImage(currentImage);
            if (currentImage != null) {
                int  pixelSize;
                pixelSize = Integer.parseInt(levels.getValue().toString());
                result.setImage(PixelImage(currentImage, pixelSize));
                File outputFile = new File(resultfolder.toString()+"\\"+(i+1)+".png");
                BufferedImage bImage = SwingFXUtils.fromFXImage(result.getImage(), null);
                ImageIO.write(bImage, "png", outputFile);
                
            }
            else{

            }
        }
        imgc.setText("Done");
    }
    
    public static WritableImage PixelImage(Image img, int pixelSize)
    {
        int height = (int)img.getHeight(), width = (int)img.getWidth();
        WritableImage result = new WritableImage(width, height);
        PixelReader pr = img.getPixelReader();
        PixelWriter pw = result.getPixelWriter();
        Color newColor, curColor = pr.getColor(0, 0);

        int x, y, sizeX, sizeY, lenH = height / pixelSize, lenW = width / pixelSize;

        int deltaH = height - lenH * pixelSize, deltaW = width - lenW * pixelSize;
        double R, G, B;

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
            {
                pw.setColor(i, j, Color.WHITE);
            }

        for (int i = 0; i < lenW; i++)
            for (int j = 0; j < lenH; j++)
            {
                R = 0;
                G = 0;
                B = 0;
                sizeX = pixelSize;
                for (x = i * pixelSize; sizeX > 0; x++, sizeX--)
                {
                    sizeY = pixelSize;
                    for (y = j * pixelSize; sizeY > 0; y++, sizeY--)
                    {
                        curColor = pr.getColor(x, y);
                        R += curColor.getRed();
                        G += curColor.getGreen();
                        B += curColor.getBlue();
                    }
                }
                newColor = new Color(R/pixelSize/pixelSize, G/pixelSize/pixelSize, B/pixelSize/pixelSize, 1.0);
                sizeX = pixelSize;
                for (x = i * pixelSize; sizeX > 0; x++, sizeX--)
                {
                    sizeY = pixelSize;
                    for (y = j * pixelSize; sizeY > 0; y++, sizeY--)
                    {
                        pw.setColor(x, y, newColor);
                    }
                }

            }

        return result;
    }

    
    
}
