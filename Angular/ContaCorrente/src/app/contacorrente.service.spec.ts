import { TestBed } from '@angular/core/testing';

import { ContacorrenteService } from './contacorrente.service';

describe('ContacorrenteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ContacorrenteService = TestBed.get(ContacorrenteService);
    expect(service).toBeTruthy();
  });
});
