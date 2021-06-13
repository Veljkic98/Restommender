import { Restaurant } from "./restaurant.model";
import { User } from "./user.model";

export class ReservationView {
    id: number;
    user: User;
    restaurant: Restaurant;
    numOfPersons: number;
    discount: number;
}