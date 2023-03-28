import { User } from "./user";

export class Event {

    eventId! : number;
    eventName! : string;
    purpose! :string;
    eventDate! : Date;
    slot! :string;
    city! : string;
    state! : string;
    country! : string;
    publish! : boolean;
    eventLink! : string;
    user! : User;
   
}
