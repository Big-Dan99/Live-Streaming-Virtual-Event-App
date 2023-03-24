import { Injectable } from '@angular/core';


import { DomSanitizer } from '@angular/platform-browser';
import { FileHandle } from '../myClasses/file-handle';

@Injectable({
  providedIn: 'root'
})
export class FileProcessingService {

  constructor( private sanitizer : DomSanitizer) { }

  public createfile(file: any){
    const thefiles: any[] = file;
    const fileHandletheFile: FileHandle[] = [];

    for (let i=0; i < thefiles.length; i++) {
      const myFileData = thefiles[i];
    const myFileBlob =  this.dataUriToBlob(myFileData.fileByte, myFileData.type);
    const myFile = new File([myFileBlob], myFileData.name, { type: myFileData.type}) ;

    const finalFileHandle: FileHandle ={
      file: myFile,
      url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(myFile))
    };

    fileHandletheFile.push(finalFileHandle);
    }

    file = fileHandletheFile;

    return file;
  }

  public dataUriToBlob(fileByte: string, fileType: any){
    const byteString = window.atob(fileByte);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Int8Array(arrayBuffer);

    for (let i = 0; i < byteString.length; i++) {
     int8Array[i] = byteString.charCodeAt(i);
    }
    const blob = new Blob([int8Array], { type: fileType});

    return blob;
  }
  
}
