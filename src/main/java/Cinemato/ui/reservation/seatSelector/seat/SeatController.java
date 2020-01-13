package main.java.Cinemato.ui.reservation.seatSelector.seat;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.java.Cinemato.models.Seat;
import main.java.Cinemato.ui.reservation.seatSelector.SeatSelectorController;

public class SeatController {

    private Seat seat;
    private boolean isSelected = false;
    private boolean isDisabled = false;


    private static SeatController instance;

    public SeatController() {
        instance = this;
    }

    public SeatController(Image available) {
        this.available = available;
    }

    public static SeatController getInstance() {
        return instance;
    }

    @FXML
    private ImageView seatImage;


    private Image selected = new Image("main/java/Cinemato/resources/assets/seat_selected.png");
    private Image available = new Image("main/java/Cinemato/resources/assets/seat.png");
    private Image disabled = new Image("main/java/Cinemato/resources/assets/seat_disabled.png");

    public void setDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;

        if (this.isDisabled) {
            this.seatImage.setImage(disabled);
            System.out.println("disabled!!");
        }
    }


    public void setSeat(Seat s) {
        this.seat = s;
    }

    public void unSelect() {
        this.isSelected = false;
    }

    public void chooseSeat(MouseEvent e) {

        if (!isDisabled) {
            if (isSelected) {
                this.seatImage.setImage(available);
                SeatSelectorController.getInstance().removeSeatsSelected(this.seat);

            } else{
                this.seatImage.setImage(selected);
                SeatSelectorController.getInstance().addSeatsSelected(this.seat);
            }

//            SeatSelectorController.getInstance().clearSelectedSeats();
            this.isSelected = !this.isSelected;
            System.out.println("Selectec seat: " + this.seat.getId());

            System.out.println(SeatSelectorController.getInstance().getSeatsSelected());
        }
    }

}
