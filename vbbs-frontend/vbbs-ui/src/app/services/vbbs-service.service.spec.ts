import { TestBed } from '@angular/core/testing';

import { VbbsServiceService } from './vbbs-service.service';

describe('VbbsServiceService', () => {
  let service: VbbsServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VbbsServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
