import { City } from "./city.model";
import { Subject } from "./subject.model";
import { Title } from "./title.model";

export interface Professor{
   id?:number;
	firstname:string;

	lastname:string;

  email:string;

  adress:string;

	city:City;

	phone:string;

  title:Title;

	reelection_date:Date;

  subjects:Subject[];
}
