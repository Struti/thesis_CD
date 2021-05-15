import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Injectable({
  providedIn: 'root',
})

export class TranslateServices {

  langs = this.translate.getLangs();

  constructor(public translate: TranslateService) {
    translate.addLangs(['hu', 'en']);
    translate.setDefaultLang('hu');
  }

  setHunLanguage(){
    return this.translate.setDefaultLang('hu');
  }

  setEngLanguage(){
    return this.translate.setDefaultLang('en');
  }
}
