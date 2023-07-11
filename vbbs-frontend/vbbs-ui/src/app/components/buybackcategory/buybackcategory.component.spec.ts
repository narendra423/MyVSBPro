import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuybackcategoryComponent } from './buybackcategory.component';

describe('BuybackcategoryComponent', () => {
  let component: BuybackcategoryComponent;
  let fixture: ComponentFixture<BuybackcategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BuybackcategoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuybackcategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
