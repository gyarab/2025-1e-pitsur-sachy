// Trida je plne vytvorena AI a upravena autorem(cesty k texturam)
// Ai cast prace.....................................................................................................................................................................................................................................
package ProjektSachy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.InputStream;
import java.util.List;

public class JavaFXtirda extends Application {

    int vybranyX = -1;
    int vybranyY = -1;

    Sachovnice sachovnice = new Sachovnice();

    GridPane grid = new GridPane();
    Label turnLabel = new Label();

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        vykresliSachovnici();

        // info kdo je na tahu
        root.setTop(turnLabel);

        // tlačítka dole
        HBox menu = new HBox(10);

        Button reset = new Button("Reset");
        Button draw = new Button("Remíza");
        Button konec = new Button("Konec");

        reset.setOnAction(e -> {
            sachovnice = new Sachovnice();
            vybranyX = -1;
            vybranyY = -1;
            vykresliSachovnici();
        });

        draw.setOnAction(e -> alert("Remíza dohodnuta"));

        konec.setOnAction(e -> {
            alert("Konec hry");
            System.exit(0);
        });

        menu.getChildren().addAll(reset, draw, konec);

        root.setCenter(grid);
        root.setBottom(menu);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sachy");
        stage.show();
    }

    private void vykresliSachovnici() {

        grid.getChildren().clear();

        // kdo je na tahu
        turnLabel.setText(sachovnice.bilyHraji ? "Tah: Bílý" : "Tah: Černý");

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {

                Figurka f = sachovnice.policko[x][y];

                Button b = new Button();
                b.setPrefSize(80, 80);

                String color = (x + y) % 2 == 0 ? "beige" : "brown";

                // vybraná figurka
                if (x == vybranyX && y == vybranyY) {
                    color = "lightblue";
                }

                // možné tahy
                if (vybranyX != -1) {
                    Figurka vybrana = sachovnice.policko[vybranyX][vybranyY];

                    if (vybrana != null) {
                        List<Pozice> tahy = vybrana.MozneTahy(sachovnice.policko, vybranyX, vybranyY);

                        for (Pozice p : tahy) {
                            if (p.x == x && p.y == y) {
                                color = "lightgreen";
                            }
                        }
                    }
                }

                b.setStyle("-fx-background-color: " + color + ";");

                // obrázky
                if (f != null) {
                    String path = "";
// Cast autora ................................................................................................................................................................................................................................................................................
                    if (f instanceof Pesec) path = f.jeBila ? "BilyPesec.png" : "CernyPesec.png";
                    if (f instanceof Vez) path = f.jeBila ? "BilaVez.png" : "CernaVez.png";
                    if (f instanceof Kun) path = f.jeBila ? "BilyKun.png" : "CernyKun.png";
                    if (f instanceof Strelec) path = f.jeBila ? "BilyStrelec.png" : "CernyStrelec.png";
                    if (f instanceof Dama) path = f.jeBila ? "BilaKralovna.png" : "CernaKralovna.png";
                    if (f instanceof Kral) path = f.jeBila ? "BilyKral.png" : "CernyKral.png";
// Ai cast ........................................................................................................................................................................................................................................................................................
                    InputStream is = getClass().getResourceAsStream("/textury/" + path);

                    if (is != null) {
                        ImageView img = new ImageView(new Image(is));
                        img.setFitWidth(60);
                        img.setFitHeight(60);
                        b.setGraphic(img);
                    }
                }

                int fx = x;
                int fy = y;

                b.setOnAction(e -> {

                    //  výběr pouze správné barvy
                    if (vybranyX == -1) {

                        Figurka kliknuta = sachovnice.policko[fx][fy];

                        if (kliknuta != null && kliknuta.jeBila == sachovnice.bilyHraji) {
                            vybranyX = fx;
                            vybranyY = fy;
                        }
                    }
                    else {

                        boolean ok = sachovnice.provedTah(vybranyX, vybranyY, fx, fy);

                        if (ok) {
                            if (sachovnice.jeMat(true)) alert("Mat! Černý vyhrál");
                            else if (sachovnice.jeMat(false)) alert("Mat! Bílý vyhrál");
                            else if (sachovnice.jePat(true) || sachovnice.jePat(false)) alert("Pat!");
                        }

                        vybranyX = -1;
                        vybranyY = -1;
                    }

                    vykresliSachovnici();
                });

                grid.add(b, y, x);
            }
        }
    }

    private void alert(String text) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(text);
        a.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
