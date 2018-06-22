export interface IReservation{
    id: number,
    user: Object,
    numberOfPersons: number,
    startDate: number,
    endDate: number,
    price: number,
    reviewed: Boolean,
    accomodation: Object,
    realized: Boolean,
    version: number
}