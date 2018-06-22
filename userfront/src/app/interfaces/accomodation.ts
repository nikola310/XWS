import { IPicture } from "./picture";

export interface IAccomodation {
    id: number,
    name: string,
    type: string,
    category: number,
    bonusServices: Array<any>,
    comments: Array<any>,
    price: number,
    prices: Array<any>,
    capacity: number,
    location: Array<any>,
    pictures: Array<IPicture>
}