import { Component, OnInit } from '@angular/core';
import { EventService } from '../myServices/event.service';
import { Event } from '../myClasses/event';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { UserAuthService } from '../myServices/user-auth.service';
import { Router } from '@angular/router';
import { UserService } from '../myServices/user.service';
import { DomSanitizer } from '@angular/platform-browser';
import { FileHandle } from '../myClasses/file-handle';
import { Files } from '../myClasses/File';

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.css']
})
export class CreateEventComponent implements OnInit {
  
  event: Event = new Event();
  userId = this.uas.getUserId();

  today= new Date().toJSON().split('T')[0];
  curr = new Date();
  tom = this.curr.getDate() + 2;
  first = this.curr.getDate() + 30;
   lday= new Date(this.curr.setDate(this.first)).toJSON().split('T')[0];

  constructor(private es: EventService, private uas: UserAuthService, private route: Router, private sanitizer: DomSanitizer, private us: UserService) { }

  file1 = [];
  file : Files = new Files (this.file1);
  file2! : FileHandle[];

  // files! : Files[] ;
  // product: Product = new Product(this.file1);

  ngOnInit(): void {
    console.log(this.uas.getUserId());
    
  }

  public prepareFormData(file: any) : FormData {
    // creer une constante de formData
        const formData = new FormData();
    
    
        // append la  partie du request (file) tenant compte du nom au backend {imagefile}
        for(var i = 0; i < this.file.fichier.length; i++ ){
          formData.append('myfile', this.file.fichier[i].file, this.file.fichier[i].file.name);
        }
  
    
        return formData;
    
      }
    
      public addFile(fileForm: NgForm) {
        // console.log(this.product);
        const fileFormData = this.prepareFormData(this.file);
    
        this.us.addFile(fileFormData).subscribe( 
          (response : any) => {
            console.log(response);
            fileForm.reset();
            this.file.fichier = [];
          },
          (error: HttpErrorResponse) => {
            console.log(error);
          }
        );
      }

  public onFileSelected(event: any){
    console.log(event);
    if(event.target.files) {
      const file41 = event.target.files[0];

      const fileHandle : FileHandle = {
        file: file41,
        url: this.sanitizer.bypassSecurityTrustUrl(
          window.URL.createObjectURL(file41)
        )
      }
      
      this.file.fichier.push(fileHandle);
    }
  }

  public addEvent(eventForm: NgForm) {
    this.es.createEvent(this.event,this.userId).subscribe(
      (response ) => {
        console.log(response);
        
        
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
    eventForm.resetForm();
  }

  // this.route.navigate(['/']);
}
