package com.assessment.repositories;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.BookingSlot;
import com.assessment.web.dto.BookingSlotDto;

public interface BookingSlotRepository extends JpaRepository<BookingSlot, Long> {

	
	@Query(value="SELECT q FROM BookingSlot q WHERE q.companyId=:companyId and q.start=:start  and q.end=:end")
	public BookingSlot findUniqueBookingSlot(@Param("companyId") String companyId, @Param("start") Date start, @Param("end") Date end);
	
	@Query(value="SELECT q FROM BookingSlot q WHERE q.companyId=:companyId and q.day=:day and q.month=:month and q.year=:year and q.disabled=false order by q.year, q.month, q.year")
	public List<BookingSlot> getBookingSlotsForDay(@Param("companyId") String companyId, @Param("day") String day, @Param("month") String month, @Param("year") String year);

	
	
	@Query(value="SELECT q FROM BookingSlot q WHERE q.companyId=:companyId  order by q.year, q.month, q.year")
	public List<BookingSlot> findAllBookingSlots(@Param("companyId") String companyId);
	
	@Query(value="SELECT q FROM BookingSlot q WHERE q.companyId=:companyId  and q.disabled=true order by q.year, q.month, q.year")
	public List<BookingSlot> findAllDisabledBookingSlots(@Param("companyId") String companyId);
	
	
	 @Query("SELECT " +
	           "    new com.assessment.web.dto.BookingSlotDto(q.day, q.month, q.year) " +
	           "FROM " +
	           "    BookingSlot q WHERE q.companyId=:companyId " +
	           "GROUP BY " +
	           "    q.day, q.month, q.year order by q.day, q.month, q.year")
	public Set<BookingSlotDto> getAllTimeCombs(@Param("companyId")String companyId);
	
	
}
