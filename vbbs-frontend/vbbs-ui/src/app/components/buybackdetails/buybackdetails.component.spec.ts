import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuybackdetailsComponent } from './buybackdetails.component';

describe('BuybackdetailsComponent', () => {
  let component: BuybackdetailsComponent;
  let fixture: ComponentFixture<BuybackdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BuybackdetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuybackdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
