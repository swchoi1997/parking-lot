package com.example.parking.domain.building.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

정public interface BuildingRepository extends JpaRepository<Building, Long> {
    boolean existsByBuildingAddressZipCode(final String zipCode);

    boolean existsByName(final String name);

    @Query(value = "select building" +
            " from Building building" +
            " where building.name = :name and building.buildingAddress.zipCode = :buildingZipCode")
    //블로그에 적을 것
    // Cannot resolve
//    해당 에러는 cirtical한 에러가 아니다. 그저 Member라는 Table의 구조를 파악할수 없다는 경고로 해결하지 않아도 정상적으로 작동한다.
//    이때 가장 간단한 해결 방법은 해당 에러를 잡지 않도록 하는 방법이다
    //https://an-thropology.tistory.com/37
    Building findByNameAndAddress(@Param("name") final String name, @Param("buildingZipCode") final String building_zipCode);
}
