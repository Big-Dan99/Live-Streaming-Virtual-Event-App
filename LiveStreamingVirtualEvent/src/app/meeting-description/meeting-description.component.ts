import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-meeting-description',
  templateUrl: './meeting-description.component.html',
  styleUrls: ['./meeting-description.component.css']
})
export class MeetingDescriptionComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit(): void {
  } 
 
}
