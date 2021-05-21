package com.korolar.itennis.service.dto.subpackage;

import com.korolar.itennis.dto.subpackage.SubPackageDto;

import java.util.List;

public interface ISubPackageDtoService {
	void addSubPackageToPlayers(List<SubPackageDto> packageDto);

	List<SubPackageDto> getAllPackagesForUser(Long id);
}
