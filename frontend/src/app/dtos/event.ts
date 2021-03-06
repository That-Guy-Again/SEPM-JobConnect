import {Address} from './address';
import {Task} from './task';
import {Employer} from './employer';
import {SuperSimpleEmployer} from './super-simple-employer';

export class Event {
  constructor(
    public id: number,
    public start: string,
    public end: string,
    public title: string,
    public description: string,
    public employer: SuperSimpleEmployer,
    public address: Address,
    public tasks: Task[]) {
  }
}
