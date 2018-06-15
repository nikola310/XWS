import { Component, OnInit } from '@angular/core';
import { AgentService } from "../agent.service";
import { UserInterface } from "../user-interface";

@Component({
  selector: 'app-agents',
  templateUrl: './agents.component.html',
  styleUrls: ['./agents.component.scss']
})
export class AgentsComponent implements OnInit {

	private msg;

	agents: UserInterface[];

	constructor(private agentService: AgentService) { }

	ngOnInit() {
		this.agentService.getAgents().subscribe(data => this.agents = data);
		console.log(this.agents);
	}

	registerAgent(data: any){
		this.msg = "{"
		+ " \"userName\":\"" + data.userName + "\","
		+ " \"firstName\":\"" + data.firstName + "\","
		+ " \"surName\":\"" + data.surName + "\","
		+ " \"userType\":\"" + data.userType + "\","
		+ " \"pid\":\"" + data.pid + "\","
		+ " \"password\":\"" + data.password +"\"}";
		console.log(this.msg);
		console.log('JEBEMU MATER!!!!!!!!!!!');
		this.agentService.newAgent(this.msg);
  }
}
