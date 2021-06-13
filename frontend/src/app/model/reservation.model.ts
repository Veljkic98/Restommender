export class Reservation {
    userId: number;
    restaurantId: number;
    numOfPersons: number;

    constructor() {
        this.numOfPersons = 0;
    }
}