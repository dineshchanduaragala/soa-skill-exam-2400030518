package com.klef.soa.exam.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.soa.exam.entity.ServiceCenter;
import com.klef.soa.exam.repository.ServiceCenterRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceCenterRepositoryTest 
{
    @Autowired
    private ServiceCenterRepository repository;

    @Test
    void testFindAllUsers() 
    {
        List<ServiceCenter> serviceCenters = repository.findAll();
        assertNotNull(serviceCenters);
        assertFalse(serviceCenters.isEmpty());
        System.out.println("Total Service Centers: " + serviceCenters.size());
    }

    @Test
    void testFindUserById() 
    {
        Optional<ServiceCenter> serviceCenter = repository.findById(1L);
        assertTrue(serviceCenter.isPresent());
        assertEquals(1L, serviceCenter.get().getId());
        System.out.println("Service Center ID: " + serviceCenter.get().getId());
        System.out.println("Name: " + serviceCenter.get().getName());
    }

    @Test
    void testFindUserByEmail() 
    {
        List<ServiceCenter> serviceCenters = repository.findByName("vehicle");
        assertNotNull(serviceCenters);
        assertFalse(serviceCenters.isEmpty());
        assertEquals("vehicle", serviceCenters.get(0).getName());
        System.out.println("Name: " + serviceCenters.get(0).getName());
        System.out.println("Type: " + serviceCenters.get(0).getType());
    }

    @Test
    void testFindUserByInvalidEmail() 
    {
        List<ServiceCenter> serviceCenters = repository.findByName("notfound");
        assertNotNull(serviceCenters);
        assertTrue(serviceCenters.isEmpty());
    }

    @Test
    void testDatabaseConnection() 
    {
        long count = repository.count();
        assertTrue(count >= 0);
        System.out.println("Total Service Centers in Database: " + count);
    }

    @Test
    void testUserDetails() 
    {
        Optional<ServiceCenter> serviceCenter = repository.findById(1L);
        assertTrue(serviceCenter.isPresent());
        ServiceCenter foundServiceCenter = serviceCenter.get();
        assertNotNull(foundServiceCenter.getName());
        assertNotNull(foundServiceCenter.getType());
        assertNotNull(foundServiceCenter.getStatus());
        assertNotNull(foundServiceCenter.getLocation());
        System.out.println("Name: " + foundServiceCenter.getName());
        System.out.println("Type: " + foundServiceCenter.getType());
        System.out.println("Status: " + foundServiceCenter.getStatus());
        System.out.println("Location: " + foundServiceCenter.getLocation());
    }

    @Test
    void testUserRole() 
    {
        Optional<ServiceCenter> serviceCenter = repository.findById(1L);
        assertTrue(serviceCenter.isPresent());
        assertEquals("motor cycle", serviceCenter.get().getType());
        System.out.println("Type: " + serviceCenter.get().getType());
    }

    @Test
    void testTotalUserCount() 
    {
        long count = repository.count();
        assertEquals(1L, count);
    }

    @Test
    void testUserRoles()
    {
        List<ServiceCenter> serviceCenters = repository.findByType("motor cycle");
        assertNotNull(serviceCenters);
        assertEquals(1, serviceCenters.size());
    }
}

