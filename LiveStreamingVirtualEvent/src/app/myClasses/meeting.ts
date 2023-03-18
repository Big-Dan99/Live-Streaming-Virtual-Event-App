import { Time } from "@angular/common";
import { User } from "./user";

export class Meeting {
    meetingId! : number;
    title! : string;
    description! : string;
    startTime! : Time;
    endTime! : Time;
    meetingDay! : Date;
    meetingLink! : string;
    organizer! : User;
    publish! : any;
}
