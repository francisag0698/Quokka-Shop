import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { PromotionService } from '../services/promotion.service';
import { Promotion } from '../models/promotion';
import { subscribeOn } from 'rxjs/operators';

@Component({
  selector: 'app-promotion',
  templateUrl: './promotion.component.html',
  styleUrls: ['./promotion.component.sass'],
  providers: [ PromotionService ]
})
export class PromotionComponent implements OnInit {

  constructor(public promotionService: PromotionService) { }

  ngOnInit() {
    this.getPromotions();
  }

  addPromotion(form: NgForm){
    if(this.promotionService.selectedPromotion.id_promotion) {
      this.promotionService.putPromotion(this.promotionService.selectedPromotion)
        .subscribe(res => {
          this.resetForm(form);
          this.getPromotions();
        });
    } else {
      this.promotionService.postPromotion(form.value)
        .subscribe(res => {
          this.getPromotions();
          this.resetForm(form);
        });
    }
  }

  editPromotion(promotion: Promotion){
    this.promotionService.selectedPromotion = promotion;
  }

  resetForm(form?: NgForm) {
    if(form) {
      form.reset();
      this.promotionService.selectedPromotion = new Promotion();
    }
  }

  getPromotions(){
    this.promotionService.getPromotion()
      .subscribe(res => {
        this.promotionService.promotions = res as Promotion[];
      });
  }
}
