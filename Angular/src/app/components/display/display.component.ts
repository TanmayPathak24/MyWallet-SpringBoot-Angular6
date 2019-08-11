import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/service';
import {Account} from '../../models/account'

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnInit {

  constructor(private service: UserService) { }

  list: Account[];

  ngOnInit() { 
    this.service.getall().subscribe(
      data=>{
        this.list = data
        console.log(this.list);
      }
    );
  }

}
