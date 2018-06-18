import { Component, OnInit } from '@angular/core';
import { AgentService } from "../agent.service";
import { UserInterface } from "../user-interface";
import { DataService } from '../data.service';

@Component({
  selector: 'app-agents',
  templateUrl: './agents.component.html',
  styleUrls: ['./agents.component.scss']
})
export class AgentsComponent implements OnInit {

	private msg;

	agents: UserInterface[];

	constructor(private agentService: AgentService, private _data: DataService) { }

	ngOnInit() {
		this.agentService.getAgents().subscribe(data => this.agents = data);
		console.log(this.agents);
	}

	acceptAgent(data: any, id: number){
		this.msg = "{"
		+ " \"accept\":\"" + true + "\","
		+ " \"pid\":\"" + data.pid + "\","
		+ " \"address\":\"" + data.address +"\"}";
		console.log(this.msg);
		this.agentService.newAgent(this.msg, id, this._data.getToken()).subscribe(
			response => console.log(response),
    		err => console.log(err)
		);
  }
  
  rejectAgent(data: any, id: number){
		this.msg = "{"
		+ " \"accept\":\"" + false + "\","
		+ " \"pid\":\"" + data.pid + "\","
		+ " \"address\":\"" + data.address +"\"}";
		console.log(this.msg);
		this.agentService.newAgent(this.msg, id, this._data.getToken()).subscribe(
			response => console.log(response),
    		err => console.log(err)
		);
  }
}
