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
		console.log('sadasda');
		this.agentService.getAgents().subscribe(data => this.agents = data);
		console.log(this.agents);
	}

	acceptAgent(pid: any, address: any, id: number,  toRemove: any){
		this.msg = "{"
		+ " \"accept\":\"" + true + "\","
		+ " \"pid\":\"" + pid + "\","
		+ " \"address\":\"" + address +"\"}";
		console.log(this.msg);
		this.agentService.newAgent(this.msg, id, sessionStorage.getItem('Token')).subscribe(
			response => {
				console.log(response);
				if (response) {
					window.alert('Accepted.');
					var i = this.agents.indexOf(toRemove);
					this.agents.splice(i, 1);
				  }else{
					window.alert('Error occurred.');
				  }
			},
    		err => console.log(err)
		);
  }
  
  rejectAgent(pid: any, address: any, id: number,  toRemove: any){
		this.msg = "{"
		+ " \"accept\":\"" + false + "\","
		+ " \"pid\":\"" + pid + "\","
		+ " \"address\":\"" + address +"\"}";
		console.log(this.msg);
		this.agentService.newAgent(this.msg, id, sessionStorage.getItem('Token')).subscribe(
			response => {
				console.log(response);
				if (response) {
					window.alert('Rejected.');
					var i = this.agents.indexOf(toRemove);
					this.agents.splice(i, 1);
				  }else{
					window.alert('Error occurred.');
				  }
			},
    		err => console.log(err)
		);
  }
}
