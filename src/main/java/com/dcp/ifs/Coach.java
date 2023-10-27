package com.dcp.ifs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public interface Coach {
    public String gatDailyWorkout();
}
@Component
@Lazy
class CricketCoach implements Coach {
    public String gatDailyWorkout() {
        System.out.println("In Constructor: " + getClass().getSimpleName());
        return "Practice fast bowling for 15 minutes!";

    }
}

@Component
class BasketBallCoach implements Coach {
    public String gatDailyWorkout() {
        System.out.println("In Constructor: " + getClass().getSimpleName());
        return "Net practice for 30 minutes!";
    }
}
@RestController
@RequestMapping("/coach")
class CoachController {
    private Coach myCoach;
    @Autowired
//    public CoachController(@Qualifier("cricketCoach") Coach theCoach) {
    public CoachController(@Qualifier("basketBallCoach") Coach theCoach) {
        System.out.println("In Constructor: " + getClass().getSimpleName());
        myCoach = theCoach;
    }
/*    @Autowired
    public void setMyCouch(Coach theCoach) {
        myCoach = theCoach;
    }*/

    @GetMapping("/getdailyworkout")
    public String getDailyWorkout() {
        return myCoach.gatDailyWorkout();
    }
}