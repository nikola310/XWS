export interface IAccomodation {
    id: number,
    name: string,
    type: string,
    category: number,
    bonusServices: Array<any>,
    price: number,
    prices: Array<any>,
    capacity: number,
    location: Array<any>
}