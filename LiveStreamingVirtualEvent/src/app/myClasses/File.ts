import { FileHandle } from "./file-handle";


export class Files {

    id! : number;
   name!:string;
    type!: string;
    fileByte! : any;

    fichier! : FileHandle[];

    // images! : FileHandle[] ;

    // constructor(images:FileHandle[]){
    //  this.images = images;
    // }
    constructor(fichier:FileHandle[]){
        this.fichier = fichier;
       }
   
}
