export interface IAccomodation {
    id: number,
    name: string,
    type: string,
    category: number,
    bonusServices: Array<any>,
    prices: Array<any>,
    capacity: number,
    location: Array<any>
}