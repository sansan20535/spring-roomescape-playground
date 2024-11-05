package roomescape.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomescapeController {

    @GetMapping("/")
    public String renderingHomePage() {
        return "home";
    }

    @GetMapping("/reservation")
    public String renderingReservationPage() {
        return "reservation";
    }

}
